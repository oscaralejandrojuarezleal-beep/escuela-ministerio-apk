/* Formularios públicos de la Escuela de Ministerio desde GitHub.
 * Baja del servidor el MISMO formulario que el link de Apps Script (ESC_apiFormPublico) y lo muestra.
 * Lo que se carga va a los mismos lugares (las mismas planillas y JSON).
 * Si el servidor no responde, pasa solo al link de Apps Script, así nunca queda trabado. */
window.ESC_FORM = function (params, linkRespaldo) {
  var API = 'https://script.google.com/macros/s/AKfycbyDsn6dRFauwni-w_nelkonynRKOR_uFSmtEV_SW9Z396vw-Z3Btoo9xjvbGWf8ciMl-A/exec';
  function mostrar(texto, conBoton) {
    document.getElementById('mensaje').textContent = texto;
    document.getElementById('rueda').classList.toggle('oculto', !!conBoton);
    document.getElementById('reintentar').classList.toggle('oculto', !conBoton);
  }
  document.getElementById('reintentar').onclick = function () { location.reload(); };
  function respaldo(motivo) {
    if (linkRespaldo) { mostrar('Abriendo el formulario…', false); location.replace(API + linkRespaldo); }
    else mostrar('No se pudo abrir el formulario: ' + motivo, true);
  }
  function preparar(html) {
    var api = '<script>window.ESC_API_URL=' + JSON.stringify(API) + ';try{localStorage.setItem("ESC_API_URL",window.ESC_API_URL)}catch(e){}<\/script>';
    return /<head[^>]*>/i.test(html) ? html.replace(/<head[^>]*>/i, function (m) { return m + api; }) : api + html;
  }
  var control = typeof AbortController === 'function' ? new AbortController() : null;
  var reloj = setTimeout(function () { if (control) control.abort(); }, 45000);
  fetch(API, {
    method: 'POST', headers: { 'Content-Type': 'text/plain;charset=utf-8' }, redirect: 'follow', cache: 'no-store',
    body: JSON.stringify({ fn: 'ESC_apiFormPublico', args: [params] }), signal: control ? control.signal : undefined
  }).then(function (r) { if (!r.ok) throw new Error('El servidor respondió ' + r.status); return r.text(); })
    .then(function (t) {
      clearTimeout(reloj);
      var d = JSON.parse(t);
      if (!d || d.ok !== true) throw new Error((d && d.error) || 'Error del servidor');
      if (!d.r || d.r.ok !== true || !d.r.html) throw new Error((d.r && d.r.error) || 'El formulario llegó vacío');
      document.open(); document.write(preparar(d.r.html)); document.close();
    })
    .catch(function (e) { clearTimeout(reloj); respaldo(e && e.message ? e.message : String(e)); });
};
