function abrirPopup(id) {
  fetch('/notas_fiscais/' + id + '/visualizar')
    .then(response => response.text())
    .then(xml => {
      const parser = new DOMParser();
      const xmlDoc = parser.parseFromString(xml, "application/xml");

      const numNota = xmlDoc.getElementsByTagName("numNota")[0]?.textContent || "";
      const tipo = xmlDoc.getElementsByTagName("tipoTransacao")[0]?.textContent || "";
      const valor = xmlDoc.getElementsByTagName("valorTotal")[0]?.textContent || "";
      const data = xmlDoc.getElementsByTagName("data")[0]?.textContent || "";

      const itens = xmlDoc.getElementsByTagName("item");
      let linhasItens = "";
      for (let i = 0; i < itens.length; i++) {
        const produto = itens[i].getElementsByTagName("produto")[0]?.textContent || "Produto";
        const quantidade = itens[i].getElementsByTagName("quantidade")[0]?.textContent || "0";
        const precoUnitario = itens[i].getElementsByTagName("precoUnitario")[0]?.textContent || "0.00";
        const totalItem = (parseFloat(precoUnitario) * parseInt(quantidade)).toFixed(2);

        linhasItens += `
          <tr>
            <td>${produto}</td>
            <td>${quantidade}</td>
            <td>R$ ${precoUnitario}</td>
            <td>R$ ${totalItem}</td>
          </tr>
        `;
      }

      // HTML final
      const danfeHTML = `
        <html>
        <head>
          <title>Nota Fiscal ${numNota}</title>
          <style>
            body { font-family: Arial, sans-serif; padding: 20px; }
            h2 { text-align: center; }
            table { border-collapse: collapse; width: 100%; margin-top: 20px; }
            th, td { border: 1px solid #000; padding: 8px; text-align: left; }
          </style>
        </head>
        <body>
          <h2>DANFE - Documento Auxiliar da Nota Fiscal Eletrônica</h2>
          <p><strong>Número:</strong> ${numNota}</p>
          <p><strong>Tipo:</strong> ${tipo}</p>
          <p><strong>Data:</strong> ${data}</p>
          <p><strong>Valor Total:</strong> R$ ${valor}</p>
          <table>
            <thead>
              <tr>
                <th>Produto</th>
                <th>Quantidade</th>
                <th>Preço Unitário</th>
                <th>Total</th>
              </tr>
            </thead>
            <tbody>
              ${linhasItens}
            </tbody>
          </table>
        </body>
        </html>
      `;

      // Abre popup
      const popup = window.open('', 'Nota Fiscal', 'width=800,height=600,scrollbars=yes');
      popup.document.write(danfeHTML);
      popup.document.close();
    });
}
