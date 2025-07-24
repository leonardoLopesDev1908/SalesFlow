function mostrarCampos() {
    const tipo = document.getElementById("tipoTransacao").value;
    const cpfDiv = document.querySelector(".cpfDiv");
    const cnpjDiv = document.querySelector(".cnpjDiv");

    if (tipo === "COMPRA") {
        cnpjDiv.style.display = "block";
        cpfDiv.style.display = "none";
    } else if (tipo === "VENDA") {
        cpfDiv.style.display = "block";
        cnpjDiv.style.display = "none";
    } else {
        cpfDiv.style.display = "none";
        cnpjDiv.style.display = "none";
    }
}

window.addEventListener('DOMContentLoaded', mostrarCampos);