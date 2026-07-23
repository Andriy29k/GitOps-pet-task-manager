package com.task_manager.app.service;

import com.task_manager.app.exception.CategoryNotFoundException;
import com.task_manager.app.model.Category;
import com.task_manager.app.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final Map<Long, Category> categories = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    private final UserService userService;

    public CategoryService(UserService userService) {
        this.userService = userService;
    }

    public List<Category> getCategories() {
        return List.copyOf(categories.values());
    }

    public List<Category> getCategoriesByUserId(long userId) {
        userService.findById(userId);
        return categories.values().stream()
                .filter(c -> c.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    public Category addCategory(long userId, String name, String color) {
        User owner = userService.findById(userId);

        Category category = new Category();
        category.setId(idGenerator.getAndIncrement());
        category.setName(name);
        category.setColor(color);
        category.setUser(owner);

        categories.put(category.getId(), category);
        return category;
    }

    public Category findById(long id) {
        Category category = categories.get(id);
        if (category == null) {
            throw new CategoryNotFoundException(id);
        }
        return category;
    }

    public Category updateCategory(long id, String name, String color) {
        Category category = findById(id);
        category.setName(name);
        category.setColor(color);
        return category;
    }

    public void deleteCategory(long id) {
        if (categories.remove(id) == null) {
            throw new CategoryNotFoundException(id);
        }
    }
}
