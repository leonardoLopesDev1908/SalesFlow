document.addEventListener('DOMContentLoaded', () => {
  const cpfInputs = document.querySelectorAll('.mask-cpf');
  cpfInputs.forEach(input => {
    IMask(input, {
      mask: '000.000.000-00'
    });
  });

  const telefoneInputs = document.querySelectorAll('.mask-telefone');
  telefoneInputs.forEach(input => {
    IMask(input, {
      mask: '(00) 00000-0000'
    });
  });
});