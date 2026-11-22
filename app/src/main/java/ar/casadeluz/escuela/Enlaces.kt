package ar.casadeluz.escuela

import android.net.Uri

/**
 * Links que pertenecen a la propia app y se abren ADENTRO de la APK (no en el navegador):
 *  - el link de Apps Script de la escuela (…/exec?modo=agenda&sesion=…, ?modo=estudiante&materia=…)
 *  - los formularios públicos de GitHub (clase.html?materia=…&clase=…, nuevos.html)
 * Devuelve la dirección local equivalente, o null si es un link de afuera (WhatsApp, Drive, etc.).
 */
object Enlaces {

    private fun idImplementacion(u: String): String =
        Regex("/macros/s/([A-Za-z0-9_-]+)/").find(u)?.groupValues?.get(1) ?: ""

    fun local(url: Uri): String? {
        val texto = url.toString()
        val propio = idImplementacion(Config.API_URL)
        if (url.host == "script.google.com" && propio.isNotEmpty() && idImplementacion(texto) == propio) {
            val modo = (url.getQueryParameter("modo") ?: "").lowercase()
            val pagina = if (modo in Config.MODOS_BETA) "beta.html" else "index.html"
            return Config.DOMINIO_LOCAL + pagina + consulta(url, setOf("_actualizar", "v", "cb"))
        }
        if (texto.startsWith(Config.GITHUB_WEB)) {
            val ruta = url.lastPathSegment ?: ""
            if (ruta == "clase.html") {
                val q = Uri.Builder().appendQueryParameter("modo", "estudiante")
                url.getQueryParameter("materia")?.let { q.appendQueryParameter("materia", it) }
                url.getQueryParameter("clase")?.let { q.appendQueryParameter("clase", it) }
                return Config.DOMINIO_LOCAL + "index.html" + q.build().toString()
            }
            if (ruta == "nuevos.html") return Config.DOMINIO_LOCAL + "beta.html?modo=nuevos_form"
        }
        return null
    }

    /** Copia los parámetros del link (sin los que sólo servían para saltear la caché del navegador). */
    private fun consulta(url: Uri, quitar: Set<String>): String {
        val b = Uri.Builder()
        var hay = false
        for (k in url.queryParameterNames) {
            if (k in quitar) continue
            url.getQueryParameters(k).forEach { b.appendQueryParameter(k, it); hay = true }
        }
        return if (hay) b.build().toString() else ""
    }
}
