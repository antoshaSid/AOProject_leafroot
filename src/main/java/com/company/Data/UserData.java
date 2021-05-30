package com.company.Data;

//Данные пользователя (любого)
public class UserData {
    public String id,phone,name,surname,key,password,description;

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
