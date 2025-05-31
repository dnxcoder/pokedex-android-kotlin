package com.example.tibiaclone.viewmodel

import android.media.MediaPlayer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.tibiaclone.data.model.Pokemon
import com.example.tibiaclone.data.network.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PokemonRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {

    //O SavedStateHandle é automaticamente preenchido com os parâmetros
    // de rota quando você usa hiltViewModel()
    // dentro de uma composable() com NavBackStackEntry.

    private val _selectedPokemon = MutableStateFlow<Pokemon?>(null);
    private val _mediaPlayer = MutableStateFlow<MediaPlayer?>(null);
    private val _audiosURL: MutableList<String> = mutableListOf();
    private var _isAboutTabSelected = MutableStateFlow<Boolean>(true);

    private var _isLatestCry: Boolean = false;

    val isAboutTabSelected: StateFlow<Boolean> = _isAboutTabSelected
    val selectedPokemon: StateFlow<Pokemon?> = _selectedPokemon;

    init {
        //getting id automatically from chosen pokemon
        val pokemonIdFromRoute = savedStateHandle.get<String>("pokemonId")?.toIntOrNull()

        if (pokemonIdFromRoute != null) {
            _selectedPokemon.value = repository.getPokemonFromCache(pokemonIdFromRoute)

            _audiosURL.add(_selectedPokemon.value!!.cries.legacy);
            _audiosURL.add(_selectedPokemon.value!!.cries.latest);


            //loading cry to mediaPlayer property
            _mediaPlayer.value = MediaPlayer().apply {
                setDataSource(_audiosURL[0])
                setOnPreparedListener { it.start() }
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
}