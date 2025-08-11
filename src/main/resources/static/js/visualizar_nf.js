

function abrirPopup(id) {
  fetch('/notas_fiscais/' + id + '/visualizar')
    .then(response => response.text())
    .then(xml => {
      const popup = window.open('', 'Nota Fiscal', 'width=800,height=600,scrollbars=yes');
      popup.document.write('<pre>' + xml.replace(/</g, '&lt;').replace(/>/g, '&gt;') + '</pre>');
      popup.document.title = 'Nota Fiscal XML';
      popup.document.close();
    });
}