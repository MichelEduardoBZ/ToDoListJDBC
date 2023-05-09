package com.michel;

import com.michel.controller.TaskController;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static TaskController taskController = new TaskController();

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
            System.out.println("Mostrar tarefa selecionado pelo ID - 5");
            System.out.println("Sair - 5");
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
                    showTaskId();
                    break;
                case 6:
                    break infinite;
            }
        }
    }

    public static void saveTask() {
        System.out.println("--- Adicione sua Task ---");
        System.out.print("Informe o nome da task: ");
        String nameTask = sc.nextLine();

        System.out.print("Digite sua task: ");
        String descriptionTask = sc.nextLine();
        try {
            taskController.newTask(nameTask, descriptionTask);
            System.out.println("Tarefa criada com sucesso!");
        } catch (RuntimeException e) {
            System.out.println("--- Atenção! --- \nNão se pode criar tarefas com o mesmo nome");
        }
    }

    public static void removeTask() {
        System.out.println("-------------------------");
        System.out.print("Qual o id da task que você deseja remover? ");

        Long id = 0L;
        try {
            id = Long.parseLong(sc.nextLine());
            if (taskController.validation(id) == null) {
                System.out.println("--- Atenção! --- \nId não encontrado");
            } else {
                taskController.deleteTask(id);
                System.out.println("Removido com sucesso");
            }
        } catch (NumberFormatException e) {
            System.out.println("--- Atenção! --- \nNenhum número informado");
        }
    }

    public static void editTask() {
        while (true) {
            Long numTask = 0L;
            System.out.print("Qual task você deseja editar? ID: ");

            try {
                numTask = Long.parseLong(sc.nextLine());
                if (taskController.validation(numTask) == null) {
                    System.out.println("--- Atenção! --- \nId não encontrado");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("--- Atenção! --- \nNenhum número informado");
                break;
            }

            System.out.print("O que você deseja editar? Nome: 1 - Task: 2 ");
            int editTaskNum = Integer.parseInt(sc.nextLine());

            if (editTaskNum == 1) {

                System.out.print("Digite o novo nome: ");
                String newNameTask = sc.nextLine();
                try {
                    taskController.editTask(numTask, editTaskNum, newNameTask);
                    System.out.println("Nome atualizado com sucesso!");
                } catch (RuntimeException e) {
                    System.out.println("--- Atenção! --- \nNão se pode criar tarefas com o mesmo nome");
                }
                break;
            } else if (editTaskNum == 2) {
                System.out.print("Digite a nova tarefa: ");
                String newTask = sc.nextLine();
                taskController.editTask(numTask, editTaskNum, newTask);
                System.out.println("Tarefa atualizada com sucesso!");
                break;
            } else {
                System.out.println("--- Atenção! ---\nOpção inexistente");
                break;
            }
        }
    }

    public static void showView() {
        System.out.println("--- Lista ---");
        System.out.println(taskController.showView());
    }

    public static void showTaskId() {
        System.out.println("--- Tarefa ---");

        Long numId;
        System.out.print("Informe o ID da task: ");
        try {
            numId = Long.parseLong(sc.nextLine());
            System.out.println(taskController.showViewId(numId));
            if (taskController.validation(numId) == null) {
                System.out.println("--- Atenção! --- \nId não encontrado");
            }
        } catch (NumberFormatException e) {
            System.out.println("--- Atenção! --- \nNenhum número informado");
        }
    }
}
