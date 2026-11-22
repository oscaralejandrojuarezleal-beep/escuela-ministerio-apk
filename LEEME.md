# APK Escuela de Ministerio (Casa de Luz)

App Android (Kotlin) que muestra **la misma app de Apps Script de la Escuela**, guardada en el celular.
Sin Firebase, 100 % gratis.

- Descarga: https://oscaralejandrojuarezleal-beep.github.io/escuela-ministerio-apk/
- APK directa: https://github.com/oscaralejandrojuarezleal-beep/escuela-ministerio-apk/releases/latest/download/Escuela-CDL.apk

## Cómo funciona

- **Una sola base de código:** la APK baja las pantallas desde el script (`ESC_apiAppApk`, con los mismos
  archivos `Index` y `Beta` de la web). Lo que cambiás en Apps Script le llega a la web y a la APK.
  - `index.html` → app principal (con `?modo=estudiante&materia=..&clase=..` el formulario del estudiante)
  - `beta.html` → Agenda / Nuevos / Dashboard (`?modo=agenda&sesion=..`, `?modo=nuevos_form`, …)
- **Links de la app adentro de la APK:** los links de Apps Script de la escuela (Agenda, Nuevos, clases) y
  los de GitHub (`clase.html`, `nuevos.html`) se abren dentro de la APK. WhatsApp, Drive, etc. se abren afuera.
- **Llamadas al servidor:** cada `google.script.run` va al mismo `/exec` por `doPost`
  (`api-rest-apk-servidor.gs`). Si se corta la señal mientras guarda, la app recupera la respuesta.
- **Actualización automática:** al hacer **Implementar → Nueva versión** en Apps Script, la "huella" de la
  app cambia sola. Android la revisa al abrir y cada 15 minutos, baja la versión nueva y pregunta
  "¿La aplicamos ahora?" (si elegís Después, queda aplicada la próxima vez que abras la app).

## Dos tipos de actualización

1. **Pantallas, estilos y lógica (Apps Script):** sólo Implementar → Nueva versión. Llegan solas.
2. **Parte Android (este repositorio):** cada cambio en `main` lo compila GitHub (pestaña **Actions**) y lo
   publica en **Releases** como `Escuela-CDL.apk`. Las apps instaladas ofrecen "Instalar".

## Archivos principales (app/src/main/java/ar/casadeluz/escuela)

| Archivo | Para qué |
|---|---|
| `Config.kt` | URL /exec del script, repositorio y web de GitHub |
| `MainActivity.kt` | La pantalla: WebView, botón Atrás, links, subir archivos/fotos |
| `Enlaces.kt` | Qué links se abren adentro de la app |
| `PaginasLocales.kt` | Sirve la app guardada en el equipo |
| `CacheExterna.kt` | Guarda librerías y fuentes externas para usar sin internet |
| `Actualizador.kt` / `RevisarApp.kt` | Bajan y aplican la versión nueva de la app (Apps Script) |
| `ActualizadorApk.kt` | Ofrece instalar la APK nueva publicada en GitHub |
| `PuenteJs.kt` | Funciones de Android que usa la app (`window.ESCAndroid`) |
| `Compartir.kt` | Compartir y guardar archivos (imágenes, PDF) |

## Firma (muy importante)

La APK se firma con una clave fija (`escuela.jks`, alias `escuela`). Si se pierde, las apps instaladas no
aceptan actualizaciones. La clave **no está en este repositorio**: está en los secretos de GitHub
(`ESC_KEYSTORE_B64`, `ESC_KEYSTORE_PASSWORD`) y en el archivo de RESPALDO de Drive.

## Costo

Cero. Repositorio público + GitHub Actions + Releases + Pages son gratis para repositorios públicos.
