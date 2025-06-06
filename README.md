# Pokedex Android

This project is a simple Pokédex application built with **Kotlin** and **Jetpack Compose**. It demonstrates basic Android architecture patterns such as dependency injection with **Hilt**, network requests with **Retrofit**, and local persistence with **Room**.

![Screenshot](app/src/main/res/drawable-v24/monster1.png)

## Features

- Fetches Pokémon data from [PokeAPI](https://pokeapi.co/).
- Displays a list of Pokémon on the home screen.
- Detail screen with statistics, abilities and a dynamic background color based on the Pokémon type.
- Option to play the Pokémon cry (and fun meme sounds for some Pokémon).
- Mark/unmark Pokémon as favorites using a local Room database.
- Favorites screen accessible from the bottom navigation bar.

## Getting Started

1. **Clone this repository**
   ```bash
   git clone https://github.com/yourname/pokedex-android-kotlin.git
   ```
2. **Open the project in Android Studio** (Giraffe or newer).
3. **Run** the `app` module on an emulator or physical device with Android 5.0+.

Gradle will handle all dependencies automatically.

## Modules and Libraries

- **Jetpack Compose** for the UI.
- **Hilt** for dependency injection.
- **Retrofit** with GSON for HTTP requests.
- **Room** for storing favorite Pokémon offline.

## Project Structure

```
app/
 ├─ data/          // network and local data sources
 ├─ domain/        // domain models and repository interfaces
 ├─ ui/            // composable screens, navigation and themes
 └─ ...
```

## License

This project is provided for educational purposes and has no official affiliation with Pokémon or Nintendo.
