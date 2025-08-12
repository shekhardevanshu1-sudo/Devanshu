import tkinter as tk
import json
import os

FILE_NAME = "tasks.json"

def load_tasks():
    if os.path.exists(FILE_NAME):
        with open(FILE_NAME, "r") as f:
            return json.load(f)
    return []

def save_tasks(tasks):
    with open(FILE_NAME, "w") as f:
        json.dump(tasks, f)

def add_task():
    task = entry.get()
    if task:
        tasks.append({"task": task, "done": False})
        save_tasks(tasks)
        entry.delete(0, tk.END)
        refresh_list()

def mark_done():
    selected = listbox.curselection()
    if selected:
        index = selected[0]
        tasks[index]["done"] = True
        save_tasks(tasks)
        refresh_list()

def delete_task():
    selected = listbox.curselection()
    if selected:
        index = selected[0]
        tasks.pop(index)
        save_tasks(tasks)
        refresh_list()

def refresh_list():
    listbox.delete(0, tk.END)
    for t in tasks:
        status = "✔" if t["done"] else "✗"
        listbox.insert(tk.END, f"{t['task']} [{status}]")

root = tk.Tk()
root.title("Task Manager")

tasks = load_tasks()

entry = tk.Entry(root, width=40)
entry.pack(pady=5)

btn_frame = tk.Frame(root)
btn_frame.pack()

tk.Button(btn_frame, text="Add Task", command=add_task).pack(side=tk.LEFT, padx=5)
tk.Button(btn_frame, text="Mark Done", command=mark_done).pack(side=tk.LEFT, padx=5)
tk.Button(btn_frame, text="Delete Task", command=delete_task).pack(side=tk.LEFT, padx=5)

listbox = tk.Listbox(root, width=50)
listbox.pack(pady=5)

refresh_list()
root.mainloop()