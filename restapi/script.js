// script.js

// !!! IMPORTANT: CHANGE THIS IP ADDRESS !!!
// Use the local IP address of your Windows machine running the server.
const SERVER_IP = "10.10.52.142";
const API_URL = `http://${SERVER_IP}:5000/todos`;

const taskList = document.getElementById('task-list');
const taskInput = document.getElementById('task-input');
const addTaskBtn = document.getElementById('add-task-btn');

// Fetches all tasks from the server and displays them
async function fetchTasks() {
    try {
        const response = await fetch(API_URL);
        const tasks = await response.json();

        taskList.innerHTML = ''; // Clear existing list
        tasks.forEach(task => {
            const li = document.createElement('li');
            li.className = 'task-item';
            if (task.done) li.classList.add('done');

            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.checked = task.done;
            checkbox.onchange = () => updateTask(task.id, checkbox.checked);

            const label = document.createElement('label');
            label.textContent = task.task;

            const deleteBtn = document.createElement('button');
            deleteBtn.textContent = 'Delete';
            deleteBtn.className = 'delete-btn';
            deleteBtn.onclick = () => deleteTask(task.id);

            li.appendChild(checkbox);
            li.appendChild(label);
            li.appendChild(deleteBtn);
            taskList.appendChild(li);
        });
    } catch (error) {
        console.error("Failed to fetch tasks:", error);
        taskList.innerHTML = '<li>Error: Could not connect to the server.</li>';
    }
}

// Adds a new task
async function addTask() {
    const taskText = taskInput.value.trim();
    if (taskText === '') return;

    await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ task: taskText }),
    });
    taskInput.value = '';
    fetchTasks();
}

// Updates a task's completion status
async function updateTask(id, isDone) {
    await fetch(`${API_URL}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ done: isDone }),
    });
    fetchTasks();
}

// Deletes a task
async function deleteTask(id) {
    await fetch(`${API_URL}/${id}`, {
        method: 'DELETE',
    });
    fetchTasks();
}

// Event listeners
addTaskBtn.addEventListener('click', addTask);
taskInput.addEventListener('keypress', (e) => e.key === 'Enter' && addTask());

// Initial load
fetchTasks();