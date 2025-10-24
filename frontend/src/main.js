
const API_URL = 'http://localhost:5050/api/tasks';



document.getElementById('task-form').addEventListener('submit',  (e) => {
    e.preventDefault();
    const taskInput = document.getElementById('task-input');
    const task = taskInput.value.trim();
    saveTask(task);
});


const getAllTasks = async () =>{
    try {
        const response = await fetch(API_URL);
        const tasks = await response.json();
        return tasks;
    } catch (error) {
        console.error('Error fetching tasks:', error);
    }
}


const renderTasks = async () =>{
    const tasks = await getAllTasks();
    const list = document.getElementById('list-tasks');
    list.innerHTML="";
    try {
        list.innerHTML += `
    ${tasks.map(task => `
      <tr>
        <th scope="row">${task.id}</th>
        <td>${task.title}</td>
        <td>${task.done ? 'Completed' : 'Pending'}</td>
        <td>
          <button class="btn btn-danger" onclick="deleteTask(${task.id})">Delete</button>
          <button class="btn btn-warning"  onclick="changeTaskStatus(${task.id})">Change status</button>
        </td>
      </tr>
    `).join('')}
    `;
    } catch (error) {
        console.error('Error rendering tasks:', error);
    }

}


const saveTask = async (title) => {
    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ title }),
        });
        const newTask = await response.json();
        renderTasks();
        return newTask;
    } catch (error) {
        console.error('Error saving task:', error);
    }
}

const deleteTask = async(id) => {
    try {
        const response = await fetch(`${API_URL}/${id}`,{
            method: 'DELETE',
        });
        if(!response.ok){
            console.error(response.error);
        }

        renderTasks();

    } catch (error) {
        console.error('Error deleting task: ', error);
    }
}


async function changeTaskStatus(id){
    try {
        const response = await fetch(`${API_URL}/toggle/${id}`,{
            method: 'PATCH',
        });

        if(!response.ok){
            console.error(response.error);
        }
        await renderTasks();

    } catch (error) {
        console.error('Error updating status task: ', error);
    }
}



window.addEventListener('DOMContentLoaded', () => {
    renderTasks();
});
