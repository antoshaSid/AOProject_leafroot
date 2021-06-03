package com.company.Data;

//Данные пользователя (любого)
public class UserData {
    public String id,phone,name,surname,key,password,description;

    public UserData(String id, String phone, String name, String surname, String key, String password, String description) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.key = key;
        this.password = password;
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", key='" + key + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
