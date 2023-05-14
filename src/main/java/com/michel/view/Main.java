package com.michel.view;

import com.michel.entities.Task;
import com.michel.taskDB.TaskDB;

import java.util.Scanner;

import static com.michel.taskDB.TaskDB.validationName;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("--- To Do List ---");

        infinite:
        while (true) {
            System.out.println("------------------------------------");
            System.out.println("O que você deseja fazer?");
            System.out.println("Adicionar uma tarefa - 1");
            System.out.println("Remover uma tarefa - 2");
            System.out.println("Editar uma tarefa - 3");
            System.out.println("Mostrar todas as tarefas - 4");
            System.out.println("Mostrar tarefa selecionado pelo nome - 5");
            System.out.println("Sair - 6");
            System.out.print("Opção: ");
            Integer optionSelected = 0;
            try {
                optionSelected = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido, utilize apenas números.");
            }

            switch (optionSelected) {
                case 1:
                    saveTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    editTask();
                    break;
                case 4:
                    showView();
                    break;
                case 5:
                    showTaskName();
                    break;
                case 6:
                    System.out.println("Saindo do programa");
                    break infinite;
            }
        }
    }

    public static void saveTask() {
        System.out.println("--- Adicione sua Task ---");
        System.out.print("Informe o nome da task: ");
        String nameTask = sc.nextLine();

        if (validationName(nameTask)) {
            System.out.println("Esse nome já está cadastrado.");
            return;
        }

        System.out.print("Digite sua task: ");
        String descriptionTask = sc.nextLine();

        try {
            TaskDB.save(new Task(nameTask, descriptionTask));
            System.out.println("Tarefa criada com sucesso!");
        } catch (RuntimeException e) {
            System.out.println("--- Atenção! --- \nNão se pode criar tarefas com o mesmo nome.");
        }
    }

    public static void removeTask() {
        System.out.println("-------------------------");
        System.out.print("Qual o nome da task que você deseja remover? ");
        String name = sc.nextLine();

        if (!validationName(name)) {
            System.out.println("--- Atenção! --- \nNome não encontrado");
            return;
        }

        TaskDB.delete(name);
        System.out.println("Removido com sucesso");

    }

    public static void editTask() {
            System.out.print("Qual task você deseja editar? Nome: ");
            String nameEdit = sc.nextLine();

            if (!validationName(nameEdit)) {
                System.out.println("--- Atenção! --- \nNome não encontrado");
                return;
            }

            System.out.print("O que você deseja editar? Nome: 1 - Task: 2 ");
            int editTaskNum = Integer.parseInt(sc.nextLine());

            if (editTaskNum == 1) {

                System.out.print("Digite o novo nome: ");
                String newNameTask = sc.nextLine();

                if (validationName(newNameTask)) {
                    System.out.println("Esse nome já está cadastrado.");
                    return;
                }

                try {
                    TaskDB.update(editTaskNum, nameEdit, newNameTask);
                    System.out.println("Nome atualizado com sucesso!");
                } catch (RuntimeException e) {
                    System.out.println("--- Atenção! --- \nNão se pode criar tarefas com o mesmo nome");
                }
            } else if (editTaskNum == 2) {
                System.out.print("Digite a nova tarefa: ");
                String newTask = sc.nextLine();
                TaskDB.update(editTaskNum, nameEdit, newTask);
                System.out.println("Tarefa atualizada com sucesso!");
            } else {
                System.out.println("--- Atenção! ---\nOpção inexistente");
        }
    }

    public static void showView() {
        System.out.println("--- Lista ---");

        if (TaskDB.selectAll().isEmpty()){
            System.out.println("Nada adicionadado");
        } else {
            System.out.println(TaskDB.selectAll());
        }
    }

    public static void showTaskName() {
        System.out.println("--- Tarefa ---");

        System.out.print("Informe o nome da task: ");
        String name = sc.nextLine();

        if (!validationName(name)) {
            System.out.println("--- Atenção! --- \nNome não encontrado");
            return;
        }

        System.out.println(TaskDB.selectName(name));
    }
}
