# Escuela de Ministerio — página pública (GitHub Pages)

Esta rama (`gh-pages`) se publica en https://oscaralejandrojuarezleal-beep.github.io/escuela-ministerio-apk/

- `index.html` — página para descargar la APK (siempre la última versión de Releases).
- `clase.html?materia=...&clase=...` — formulario del estudiante (el mismo que `?modo=estudiante` de Apps Script).
- `nuevos.html` — formulario de nuevos (el mismo que `?modo=nuevos_form`).

Los formularios se bajan del servidor en el momento (`ESC_apiFormPublico`), así que cualquier cambio
publicado en Apps Script se ve acá al instante. Si el servidor no responde, la página pasa sola al link de
Apps Script. Los links viejos de Apps Script siguen funcionando igual.

Esta rama no toca la APK: la APK se compila sólo desde `main`.
