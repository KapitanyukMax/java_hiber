package org.example;

import org.example.models.Category;
import org.example.models.Product;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        Scanner scanner = new Scanner(System.in);
        int action = 0;

        do {
            System.out.println("0 - Exit");
            System.out.println("1 - Add category");
            System.out.println("2 - Show categories");
            System.out.println("3 - Delete category");
            System.out.println("4 - Update category");
            System.out.println("5 - Add product");
            System.out.println("6 - Show products");
            System.out.println("7 - Delete product");
            System.out.println("8 - Update product");
            System.out.print("->_");
            action = Integer.parseInt(scanner.nextLine());

            switch (action) {
                case 1: {
                    AddCategory();
                    break;
                }
                case 2: {
                    ShowCategories();
                    break;
                }
                case 3: {
                    DeleteCategory();
                    break;
                }
                case 4: {
                    UpdateCategory();
                    break;
                }
                case 5: {
                    AddProduct();
                    break;
                }
                case 6: {
                    ShowProducts();
                    break;
                }
                case 7: {
                    DeleteProduct();
                    break;
                }
                case 8: {
                    UpdateProduct();
                    break;
                }
            }
        } while (action != 0);
    }

    private static void AddCategory() {
        Scanner scanner = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();
        SessionFactory sf = HibernateUtil.getSessionFactory();

        try (Session context = sf.openSession()) {
            context.beginTransaction();
            Category category = new Category();

            System.out.print("Enter category name: ");
            category.setName(scanner.nextLine());

            System.out.print("Enter category image name: ");
            category.setImage("/images/" + scanner.nextLine());

            category.setDateCreated(calendar.getTime());
            context.save(category);
            context.getTransaction().commit();
        }
    }

    private static void ShowCategories() {
        SessionFactory sf = HibernateUtil.getSessionFactory();

        try (Session context = sf.openSession()) {
            context.beginTransaction();

            List<Category> list = context.createQuery("from Category", Category.class)
                    .getResultList();
            for (Category category : list) {
                System.out.println("Category: " + category);
            }

            context.getTransaction().commit();
        }
    }

    private static void DeleteCategory() {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sf = HibernateUtil.getSessionFactory();

        try (Session context = sf.openSession()) {
            context.beginTransaction();

            System.out.print("Enter category id: ");
            Category category = context.get(Category.class, Integer.parseInt(scanner.nextLine()));
            context.delete(category);

            context.getTransaction().commit();
        }
    }

    private static void UpdateCategory() {
        Scanner scanner = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();
        SessionFactory sf = HibernateUtil.getSessionFactory();

        try (Session context = sf.openSession()) {
            context.beginTransaction();

            System.out.print("Enter category id: ");
            Category category = context.get(Category.class, Integer.parseInt(scanner.nextLine()));

            System.out.print("Enter new category name: ");
            category.setName(scanner.nextLine());

            System.out.print("Enter new category image name: ");
            category.setImage("/images/" + scanner.nextLine());

            context.update(category);
            context.getTransaction().commit();
        }
    }

    private static void AddProduct() {
        Scanner scanner = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();
        SessionFactory sf = HibernateUtil.getSessionFactory();

        try (Session context = sf.openSession()) {
            context.beginTransaction();
            Product product = new Product();

            System.out.print("Enter product name: ");
            product.setName(scanner.nextLine());

            System.out.print("Enter product description: ");
            product.setDescription(scanner.nextLine());

            System.out.print("Enter product image name: ");
            product.setImage("/images/" + scanner.nextLine());

            System.out.print("Enter product price: ");
            product.setPrice(Double.parseDouble(scanner.nextLine()));

            System.out.print("Enter product category id: ");
            Category category = new Category();
            category.setId(Integer.parseInt(scanner.nextLine()));
            product.setCategory(category);

            context.save(product);
            context.getTransaction().commit();
        }
    }

    private static void ShowProducts() {
        SessionFactory sf = HibernateUtil.getSessionFactory();

        try (Session context = sf.openSession()) {
            context.beginTransaction();

            List<Product> list = context.createQuery("from Product", Product.class)
                    .getResultList();
            for (Product product : list) {
                System.out.println("Product: " + product);
            }

            context.getTransaction().commit();
        }
    }

    private static void DeleteProduct() {
        Scanner scanner = new Scanner(System.in);
        SessionFactory sf = HibernateUtil.getSessionFactory();

        try (Session context = sf.openSession()) {
            context.beginTransaction();

            System.out.print("Enter product id: ");
            Product product = context.get(Product.class, Integer.parseInt(scanner.nextLine()));
            context.delete(product);

            context.getTransaction().commit();
        }
    }

    private static void UpdateProduct() {
        Scanner scanner = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();
        SessionFactory sf = HibernateUtil.getSessionFactory();

        try (Session context = sf.openSession()) {
            context.beginTransaction();

            System.out.print("Enter product id: ");
            Product product = context.get(Product.class, Integer.parseInt(scanner.nextLine()));

            System.out.print("Enter new product name: ");
            product.setName(scanner.nextLine());

            System.out.print("Enter new product description: ");
            product.setDescription(scanner.nextLine());

            System.out.print("Enter new product image name: ");
            product.setImage("/images/" + scanner.nextLine());

            System.out.print("Enter new product price: ");
            product.setPrice(Double.parseDouble(scanner.nextLine()));

            System.out.print("Enter new product category id: ");
            Category category = new Category();
            category.setId(Integer.parseInt(scanner.nextLine()));
            product.setCategory(category);

            context.update(product);
            context.getTransaction().commit();
        }
    }
}
