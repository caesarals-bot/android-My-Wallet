# My Wallet - Rastreador de Precios de Bitcoin

**My Wallet** es una aplicación moderna para Android, desarrollada en Kotlin, que permite a los usuarios seguir el valor diario del Bitcoin. La aplicación cuenta con una interfaz de usuario limpia y amigable, gestión segura de sesiones y obtención de datos en tiempo real desde una API pública.

**Creado por:** César Londoño

---

## 📸 Screenshots

*Nota: Para añadir las capturas, crea una carpeta `screenshots` en la raíz del proyecto y guarda las imágenes con los nombres correspondientes.*

| SplashScreen | HomeScreen | LoginScreen | MainScreen |
| :----------: | :----------: | :-----------: | :----------: |
| ![SplashScreen](screenshots/splash.png) | ![HomeScreen](screenshots/home.png) | ![LoginScreen](screenshots/login.png) | ![MainScreen](screenshots/main.png) |

---

## ✨ Características Principales

- **Pantalla de Bienvenida Atractiva**: Una `SplashScreen` animada que mejora la experiencia inicial del usuario.
- **Login de Usuario**: Sistema de inicio de sesión simple (`usuario: admin`, `clave: 1234`).
- **Sesión Persistente**: La aplicación recuerda tu sesión gracias a `DataStore`. No es necesario iniciar sesión cada vez que abres la app.
- **Valor de Bitcoin en la Pantalla de Inicio**: La pantalla `Home` muestra el valor más reciente del Bitcoin para captar el interés del usuario antes de iniciar sesión.
- **Historial de Valores de Bitcoin**: Tras iniciar sesión, los usuarios pueden ver una lista con los valores históricos del Bitcoin, actualizados diariamente.
- **Funcionalidad de Cierre de Sesión**: Los usuarios pueden cerrar sesión de forma segura, borrando los datos de su sesión del dispositivo.
- **UI Moderna y Reactiva**: Construida 100% con Jetpack Compose, siguiendo los principios de Material Design 3.

---

## 🏗️ Arquitectura y Estructura del Proyecto

Este proyecto sigue los principios de **Clean Architecture** y el patrón de diseño **MVVM (Model-View-ViewModel)**. La estructura del código está organizada para maximizar la separación de responsabilidades, la testeabilidad y la mantenibilidad.

```
/app/src/main/java/com/ideasprojects/cesar_londono_20250807
├── data
│   ├── remote
│   │   └── MindicadorApiService.kt  # Interfaz de Retrofit para la API
│   └── repository
│       ├── BitcoinRepository.kt     # Repositorio para los datos de Bitcoin
│       └── UserPreferencesRepository.kt # Repositorio para DataStore
├── domain
│   └── model
│       └── BitcoinModels.kt         # Data classes que representan los datos
└── presentation
    ├── home
    │   ├── HomeActivity.kt, HomeScreen.kt, HomeViewModel.kt, state/HomeState.kt
    ├── login
    │   ├── LoginActivity.kt, LoginScreen.kt, LoginViewModel.kt, state/LoginState.kt
    ├── main
    │   ├── MainActivity.kt, MainScreen.kt, MainViewModel.kt, state/MainState.kt
    └── splash
        └── SplashActivity.kt, SplashScreen.kt, SplashViewModel.kt
```

- **`data`**: Capa de datos. Su responsabilidad es ser la única fuente de verdad para los datos de la aplicación. Contiene las implementaciones de los repositorios y las fuentes de datos (API remota, base de datos local, etc.).
- **`domain`**: Capa de dominio. Contiene la lógica de negocio y los modelos de datos principales de la aplicación. Es una capa pura de Kotlin, independiente de Android.
- **`presentation`**: Capa de presentación. Contiene toda la lógica relacionada con la UI (Vistas y ViewModels). Se comunica con la capa de dominio para mostrar datos y reaccionar a las interacciones del usuario.

---

## 🛠️ Tecnologías y Librerías Utilizadas

- **Lenguaje**: [Kotlin](https://kotlinlang.org/)
- **UI Toolkit**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Asincronía**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- **Peticiones de Red**: [Retrofit 2](https://square.github.io/retrofit/)
- **(De)serialización de JSON**: [Gson](https://github.com/google/gson)
- **Persistencia Local**: [Jetpack DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
- **Componentes de Arquitectura de Android**: `ViewModel`, `StateFlow`.

---

## 🔌 API de Referencia

Los datos del valor del Bitcoin se obtienen de la API pública [Mindicador.cl](https://mindicador.cl/api/bitcoin).

---

## 🚀 Cómo Empezar

1.  Clona el repositorio:
    ```bash
    git clone https://github.com/tu-usuario/cesar_londono_20250807.git
    ```
2.  Abre el proyecto en Android Studio.
3.  Sincroniza las dependencias de Gradle.
4.  Ejecuta la aplicación en un emulador o dispositivo físico.

¡Y listo para usar!
