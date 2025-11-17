document.addEventListener('DOMContentLoaded', function() {

    const ctxMunicipio = document.getElementById('graficaTurnosPorMunicipio');
    if (ctxMunicipio) {
        new Chart(ctxMunicipio, {
            type: 'bar',
            data: {
                labels: turnosPorMunicipioLabels,
                datasets: [{
                    label: 'Total de Turnos',
                    data: turnosPorMunicipioData,
                    backgroundColor: 'rgba(54, 162, 235, 0.6)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                },
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: false
                    }
                }
            }
        });
    }

    const ctxAsunto = document.getElementById('graficaTurnosPorAsunto');
    if (ctxAsunto) {
        new Chart(ctxAsunto, {
            type: 'doughnut',
            data: {
                labels: turnosPorAsuntoLabels,
                datasets: [{
                    label: 'Total de Turnos',
                    data: turnosPorAsuntoData,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)',
                        'rgba(255, 159, 64, 0.6)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    }
                }
            }
        });
    }

    const ctxEstado = document.getElementById('graficaTurnosPorEstado');
    if (ctxEstado) {
        new Chart(ctxEstado, {
            type: 'bar', 
            data: {
                labels: turnosPorEstadoLabels,
                datasets: [{
                    label: 'Cantidad de Turnos',
                    data: turnosPorEstadoData,
                    backgroundColor: [
                        'rgba(255, 206, 86, 0.6)', 
                        'rgba(75, 192, 192, 0.6)', 
                        'rgba(255, 99, 132, 0.6)'  
                    ],
                    borderColor: [
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(255, 99, 132, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                },
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: false
                    }
                }
            }
        });
    }
});