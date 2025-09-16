# server.py
from flask import Flask, request, jsonify
from flask_cors import CORS

# Initialize the Flask application
app = Flask(__name__)
# Enable CORS to allow requests from your VM
CORS(app)

# In-memory "database"
todos = [
    {"id": 1, "text": "Run server on local system"},
    {"id": 2, "text": "Run website on VM"}
]
next_id = 3

# --- API Endpoints ---

# GET /tasks: Retrieve all tasks
@app.route('/tasks', methods=['GET'])
def get_tasks():
    """Returns the list of all todo items."""
    return jsonify(todos)

# POST /tasks: Create a new task
@app.route('/tasks', methods=['POST'])
def add_task():
    """Adds a new task to the list."""
    global next_id
    if not request.json or 'text' not in request.json:
        return jsonify({"error": "Missing 'text' in request body"}), 400
    
    task_text = request.json['text']
    
    new_task = {
        'id': next_id,
        'text': task_text
    }
    todos.append(new_task)
    next_id += 1
    return jsonify(new_task), 201

# --- Run the App ---

if __name__ == '__main__':
    # Running on all addresses on port 5000
    app.run(host='0.0.0.0', port=5000, debug=True)