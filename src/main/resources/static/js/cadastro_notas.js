let contadorItens = 0;

function adicionarItem() {
    const produtoId = document.getElementById("produtoId").value;
    const quantidade = document.getElementById("quantidade").value;
    const precoUnitario = document.getElementById("precoUnitario").value;

    if (!produtoId || !quantidade || !precoUnitario) {
        alert("Preencha todos os campos do item.");
        return;
    }

    const tabela = document.getElementById("itensTabela").getElementsByTagName("tbody")[0];

    const novaLinha = tabela.insertRow();
    novaLinha.innerHTML = `
        <td>${produtoId}</td>
        <td>${quantidade}</td>
        <td>${precoUnitario}</td>
        <td><button type="button" onclick="removerItem(this)">Remover</button></td>
    `;

    const form = document.getElementById("notaFiscalForm");

    form.insertAdjacentHTML("beforeend", `
        <input type="hidden" name="itens[${contadorItens}].produtoId" value="${produtoId}" />
        <input type="hidden" name="itens[${contadorItens}].quantidade" value="${quantidade}" />
        <input type="hidden" name="itens[${contadorItens}].precoUnitario" value="${precoUnitario}" />
    `);

    contadorItens++;

    document.getElementById("produtoId").value = "";
    document.getElementById("quantidade").value = "";
    document.getElementById("precoUnitario").value = "";
}

function removerItem(botao) {
    const linha = botao.parentElement.parentElement;
    const index = Array.from(linha.parentElement.children).indexOf(linha);

    linha.remove();

    const form = document.getElementById("notaFiscalForm");
    const inputs = form.querySelectorAll(`input[name^="itens[${index}]."]`);
    inputs.forEach(i => i.remove());
}