(function () {
  function abrirPopupPedido(numPedido) {
    console.log("Abrindo pedido:", numPedido); // debug

    const modal = document.getElementById("pedidoModal");
    fetch(`/pedido/${numPedido}`)
      .then(r => { if (!r.ok) throw new Error("Erro ao buscar pedido"); return r.json(); })
      .then(p => {
        document.getElementById("modalNumPedido").innerText = p.numPedido ?? "";
        document.getElementById("modalTitulo").innerText = p.titulo ?? "";
        document.getElementById("modalUsuario").innerText = p.nomeUsuario ?? "";
        document.getElementById("modalDepartamento").innerText = p.departamento ?? "";
        document.getElementById("modalData").innerText = p.dataFormatada ?? "";
        document.getElementById("modalStatus").innerText = p.status ?? "";
        document.getElementById("modalDescricao").innerText = p.descricao ?? "";

        document.getElementById("btnAprovar").onclick = () =>aprovarPedido(p.numPedido);
        document.getElementById("btnNegar").onclick = () =>negarPedido(p.numPedido);

        modal.style.display = "block";
      })
      .catch(err => alert("Não foi possível carregar o pedido: " + err.message));
  }

  function fecharPopupPedido() {
    document.getElementById("pedidoModal").style.display = "none";
  }

  function negarPedido(numPedido){
    fetch(`/pedido/${numPedido}/status?novoStatus=NEGADO`, {method: "PUT"})
      .then(r => {
        if(!r.ok) throw new Error("Erro ao negar o pedido");
        document.getElementById("modalStatus").textContent = "NEGADO"
      })
      .catch(err => alert(err.message));
  }

  function aprovarPedido(numPedido){
     fetch(`/pedido/${numPedido}/status?novoStatus=APROVADO`, { method: "PUT" })
      .then(r => {
        if (!r.ok) throw new Error("Erro ao aprovar pedido");
        document.getElementById("modalStatus").textContent = "APROVADO";
      })
      .catch(err => alert(err.message));
  }

  window.abrirPopupPedido = abrirPopupPedido;
  window.fecharPopupPedido = fecharPopupPedido;
})();
