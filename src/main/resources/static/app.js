const API_URL = 'http://localhost:8080/api/pacientes';

// Obtener elementos del DOM del formulario de registro
const pacienteForm = document.getElementById('pacienteForm');
const pacienteId = document.getElementById('pacienteId');
const nombre = document.getElementById('nombre');
const edad = document.getElementById('edad');
const telefono = document.getElementById('telefono');
const direccion = document.getElementById('direccion');
const motivoConsulta = document.getElementById('motivoConsulta');
const submitBtn = document.getElementById('submitBtn');

// Obtener elementos del DOM para la tabla de listado
const pacientesBody = document.getElementById('pacientesBody');

// Función para registrar o actualizar un paciente
if (pacienteForm) {
    pacienteForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        const paciente = {
            nombre: nombre.value,
            edad: parseInt(edad.value),
            telefono: telefono.value,
            direccion: direccion.value,
            motivoConsulta: motivoConsulta.value
        };

        let method = 'POST';
        let url = `${API_URL}/registrar`;

        if (pacienteId.value) {  // Si estamos editando
            method = 'PUT';
            url = `${API_URL}/actualizar/${pacienteId.value}`;
        }

        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(paciente)
        });

        if (response.ok) {
            if (pacienteForm) pacienteForm.reset();
            submitBtn.textContent = 'Registrar Paciente';
            if (pacientesBody) obtenerPacientes();  // Refrescar la lista
        }
    });
}

// Función para obtener todos los pacientes
async function obtenerPacientes() {
    const response = await fetch(`${API_URL}/listar`);
    const pacientes = await response.json();
    pacientesBody.innerHTML = '';  // Limpiamos la tabla
    pacientes.forEach(paciente => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${paciente.nombre}</td>
            <td>${paciente.edad}</td>
            <td>${paciente.telefono}</td>
            <td>${paciente.direccion}</td>
            <td>${paciente.motivoConsulta}</td>
            <td>
                <button onclick="editarPaciente(${paciente.id})">Editar</button>
                <button onclick="eliminarPaciente(${paciente.id})">Eliminar</button>
            </td>
        `;
        pacientesBody.appendChild(row);
    });
}

// Cargar lista de pacientes si estamos en el listado
if (pacientesBody) {
    document.addEventListener('DOMContentLoaded', obtenerPacientes);
}

// Función para editar un paciente
async function editarPaciente(id) {
    const response = await fetch(`${API_URL}/${id}`);
    const paciente = await response.json();

    pacienteId.value = paciente.id;
    nombre.value = paciente.nombre;
    edad.value = paciente.edad;
    telefono.value = paciente.telefono;
    direccion.value = paciente.direccion;
    motivoConsulta.value = paciente.motivoConsulta;
    submitBtn.textContent = 'Actualizar Paciente';
}

// Función para eliminar un paciente
async function eliminarPaciente(id) {
    const response = await fetch(`${API_URL}/eliminar/${id}`, {
        method: 'DELETE'
    });
    if (response.ok) {
        obtenerPacientes();  // Refrescar la lista
    }
}
