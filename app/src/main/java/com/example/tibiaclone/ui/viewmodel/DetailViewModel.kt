package com.example.tibiaclone.ui.viewmodel

import android.media.MediaPlayer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tibiaclone.R
import com.example.tibiaclone.domain.model.Pokemon
import com.example.tibiaclone.domain.repository.PokemonRepository
import com.example.tibiaclone.domain.repository.FavoritesRepository
import com.example.tibiaclone.data.statics.memesSounds
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val favoritesRepository: FavoritesRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    //O SavedStateHandle é automaticamente preenchido com os parâmetros
    // de rota quando você usa hiltViewModel()
    // dentro de uma composable() com NavBackStackEntry.

    private val _selectedPokemon = MutableStateFlow<Pokemon?>(null);
    private val _mediaPlayer = MutableStateFlow<MediaPlayer?>(null);
    private val _audiosURL: MutableList<String> = mutableListOf();
    private var _isAboutTabSelected = MutableStateFlow<Boolean>(true);
    private val _isFavorite = MutableStateFlow(false)

    private var _isLatestCry: Boolean = false;

    val isAboutTabSelected: StateFlow<Boolean> = _isAboutTabSelected
    val selectedPokemon: StateFlow<Pokemon?> = _selectedPokemon;
    val isFavorite: StateFlow<Boolean> = _isFavorite

    init {
        //getting id automatically from chosen pokemon
        val pokemonIdFromRoute = savedStateHandle.get<String>("pokemonId")?.toIntOrNull()

        if (pokemonIdFromRoute != null) {
            _selectedPokemon.value = repository.getPokemonFromCache(pokemonIdFromRoute)
            viewModelScope.launch {
                _isFavorite.value = favoritesRepository.getPokemon(pokemonIdFromRoute) != null
            }

            _audiosURL.add(_selectedPokemon.value!!.cries.legacy);
            _audiosURL.add(_selectedPokemon.value!!.cries.latest);


            //loading cry to mediaPlayer property
            _mediaPlayer.value = MediaPlayer().apply {
                setDataSource(_audiosURL[0])
                setOnPreparedListener {
                    it.start()
                }
//                setOnCompletionListener {
//                    it.release()
//                    _mediaPlayer.value = null
//                }
                prepareAsync()
            }

        }
    }

    fun toggleTab() {
        _isAboutTabSelected.value = !isAboutTabSelected.value
    }

    fun playCryFromPokemon() {
        _mediaPlayer.value = MediaPlayer().apply {
            setDataSource(if (_isLatestCry) _audiosURL[0] else _audiosURL[1])
            setOnPreparedListener { it.start() }
            setOnCompletionListener {
                it.release()
                _mediaPlayer.value = null
            }
            prepareAsync()
        }
        _isLatestCry = !_isLatestCry
    }

    fun toggleFavorite() {
        val pokemon = _selectedPokemon.value ?: return
        viewModelScope.launch {
            if (_isFavorite.value) {
                favoritesRepository.removePokemon(pokemon.id)
            } else {
                favoritesRepository.addPokemon(pokemon)
            }
            _isFavorite.value = !_isFavorite.value
        }
    }


    fun playZeDaManga(pokemon: Pokemon) {

        if(pokemon.id < memesSounds.size){
            _mediaPlayer.value = MediaPlayer().apply {
                //setDataSource("https://www.myinstants.com/media/sounds/ze-da-manga_G3QwWGi.mp3")
                setDataSource(memesSounds[pokemon.id]);

                setOnPreparedListener { it.start() }
                setOnCompletionListener {
                    it.release()
                    _mediaPlayer.value = null
                }
                prepareAsync()
            }
        }
        else{
            playCryFromPokemon()
        }
    }
}