# server.py
from flask import Flask, request, jsonify
from flask_cors import CORS

app = Flask(__name__)
# Allow requests from any origin, which is needed for your VM to connect.
CORS(app)

# In-memory "database"
todos = [
    {"id": 1, "task": "Set up the backend server", "done": True},
    {"id": 2, "task": "Create the website client", "done": False}
]
next_id = 3

# --- API Endpoints ---

# GET /todos: Retrieve all tasks
@app.route('/todos', methods=['GET'])
def get_todos():
    return jsonify(todos)

# POST /todos: Create a new task
@app.route('/todos', methods=['POST'])
def add_todo():
    global next_id
    if not request.json or 'task' not in request.json:
        return jsonify({"error": "Missing 'task' in request body"}), 400

    new_todo = {
        'id': next_id,
        'task': request.json['task'],
        'done': False
    }
    todos.append(new_todo)
    next_id += 1
    return jsonify(new_todo), 201

# PUT /todos/<int:todo_id>: Update a task's status
@app.route('/todos/<int:todo_id>', methods=['PUT'])
def update_todo(todo_id):
    task = next((t for t in todos if t['id'] == todo_id), None)
    if task is None:
        return jsonify({"error": "Task not found"}), 404

    is_done = request.json.get('done', task['done'])
    task['done'] = is_done
    return jsonify(task)

# DELETE /todos/<int:todo_id>: Delete a task
@app.route('/todos/<int:todo_id>', methods=['DELETE'])
def delete_todo(todo_id):
    global todos
    task_exists = any(t['id'] == todo_id for t in todos)
    if not task_exists:
        return jsonify({"error": "Task not found"}), 404

    todos = [t for t in todos if t['id'] != todo_id]
    return jsonify({"result": True})

# --- Run the App ---

if __name__ == '__main__':
    # host='0.0.0.0' makes the server accessible from other devices on your network
    app.run(host='0.0.0.0', port=5000, debug=True)