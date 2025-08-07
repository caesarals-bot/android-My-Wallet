# My Wallet - Aplicación de Billetera Virtual Bitcoin

Este documento describe los requisitos y la arquitectura para la aplicación "My Wallet", una billetera virtual enfocada en Bitcoin.

## Requisitos Funcionales

1.  **Pantalla de Bienvenida (SplashScreen)**:
    *   La aplicación debe iniciar con una pantalla de bienvenida (SplashScreen) alusiva a los indicadores económicos o a la billetera virtual.

2.  **Autenticación de Usuario**:
    *   Debe contar con una pantalla de login con campos para usuario y contraseña.
    *   Tras un inicio de sesión exitoso, las pantallas posteriores deben mostrar el mensaje "Bienvenido: <USUARIO>", obteniendo el nombre de usuario desde las preferencias.

3.  **Persistencia de Sesión**:
    *   Si un usuario ya ha iniciado sesión y cierra la aplicación, no se le deben solicitar las credenciales nuevamente al volver a abrirla.

4.  **Cierre de Sesión**:
    *   La aplicación debe contar con un botón que permita al usuario cerrar su sesión.

5.  **Visualización de Datos de Bitcoin**:
    *   Obtener los valores de bitcoin desde la API pública: `https://mindicador.cl/api/bitcoin`.
    *   La llamada a la API debe realizarse de forma asíncrona (utilizando hilos, corutinas o AsyncTask).
    *   Los datos obtenidos deben ser deserializados y mostrados en un formato de lista.

## Arquitectura Sugerida: MVVM + Repository

Se utilizará el patrón de arquitectura Model-View-ViewModel (MVVM) con un Repositorio para separar las responsabilidades y facilitar el mantenimiento y la escalabilidad de la aplicación.

### Componentes Clave

*   **View (Vistas)**:
    *   `SplashScreenActivity`: Pantalla de carga inicial.
    *   `LoginActivity`: Pantalla para el inicio de sesión del usuario.
    *   `MainActivity`: Pantalla principal que muestra el mensaje de bienvenida y la lista con los valores de Bitcoin.

*   **ViewModel (VM)**:
    *   Orquesta la lógica de la interfaz de usuario (UI).
    *   Expone el estado de la UI y los datos (como la información del usuario y los valores de Bitcoin) a las Vistas a través de `LiveData` o `StateFlow`.

*   **Repository (Repositorio)**:
    *   Encapsula y gestiona las fuentes de datos, proveyendo una API de datos limpia al resto de la aplicación.
    *   Decide si obtener los datos desde una fuente remota o local.

*   **Data Sources (Fuentes de Datos)**:
    *   **Remote**: Realiza la llamada HTTP a la API `https://mindicador.cl/api/bitcoin` para obtener los datos actualizados de Bitcoin.
    *   **Local**: Gestiona la persistencia de la sesión del usuario (estado de login y nombre de usuario) utilizando `SharedPreferences` o, preferiblemente para nuevos desarrollos, `DataStore`.
