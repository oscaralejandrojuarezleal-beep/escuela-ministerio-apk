package ar.casadeluz.escuela

/**
 * Configuración de la APK.
 *
 * API_URL: la URL de la implementación de Apps Script (la que termina en /exec).
 * La muestra ESC_API_PRUEBA() en el registro del editor ("urlParaLaApk").
 */
object Config {
    const val API_URL = "https://script.google.com/macros/s/AKfycbyDsn6dRFauwni-w_nelkonynRKOR_uFSmtEV_SW9Z396vw-Z3Btoo9xjvbGWf8ciMl-A/exec"

    /** Repositorio de GitHub donde se compila y publica la APK (actualizaciones de la parte Android). */
    const val GITHUB_REPO = "oscaralejandrojuarezleal-beep/escuela-ministerio-apk"

    /** Web de GitHub (formularios públicos): sus links también se abren adentro de la APK. */
    const val GITHUB_WEB = "https://oscaralejandrojuarezleal-beep.github.io/escuela-ministerio-apk/"

    /** Dirección interna desde donde el WebView carga la app (no es internet). */
    const val DOMINIO_LOCAL = "https://appassets.androidplatform.net/"
    const val URL_INICIO = DOMINIO_LOCAL + "index.html"

    /** Nombre del archivo de preferencias interno. */
    const val PREFS = "escuela_apk"

    /** Nombre con el que la app web ve a Android: window.ESCAndroid */
    const val PUENTE = "ESCAndroid"

    /** Función del servidor que entrega la app completa. */
    const val FN_APP = "ESC_apiAppApk"

    /** Función liviana que devuelve la "huella" de la app publicada. */
    const val FN_HUELLA = "ESC_apiHuellaApp"

    /** Nombre del archivo APK publicado en GitHub. */
    const val ARCHIVO_APK = "Escuela-CDL.apk"

    /** Nombre que se muestra al imprimir, notificar y guardar. */
    const val NOMBRE = "Escuela de Ministerio"

    /** Pantallas de beta.html (Agenda, Nuevos, Dashboard). El resto va a index.html. */
    val MODOS_BETA = setOf("beta", "agenda", "nuevos", "nuevos_lista", "nuevos_form", "dashboard")
}
