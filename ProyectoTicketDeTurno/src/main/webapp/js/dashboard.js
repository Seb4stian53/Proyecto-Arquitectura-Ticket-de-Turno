// Ejemplo para futuras gráficas
// Puedes usar Chart.js o similar
document.addEventListener("DOMContentLoaded", () => {
    const canvas = document.getElementById("graficaTurnos");
    if (!canvas) return;
    const ctx = canvas.getContext("2d");
    ctx.fillStyle = "#004aad";
    ctx.fillRect(50, 100, 100, 200);
    ctx.fillText("Gráfica de ejemplo", 60, 80);
});
// Código para manejar la lógica del dashboard