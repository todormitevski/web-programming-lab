package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.StringJoiner;

@Converter
public class UserFullnameConverter implements AttributeConverter<UserFullname, String> {

    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(UserFullname userFullname) {
        if(userFullname == null) return null;

        StringJoiner sj = new StringJoiner(SEPARATOR);
        if(userFullname.getSurname() != null &&
        !userFullname.getSurname().isEmpty())
            sj.add(userFullname.getSurname());
        if(userFullname.getName() != null &&
        !userFullname.getName().isEmpty())
            sj.add(userFullname.getName());

        return sj.toString();
    }

    @Override
    public UserFullname convertToEntityAttribute(String s) {
        if(s == null || s.isEmpty()) return null;

        String[] parts = s.split(SEPARATOR);
        if(parts.length == 0) return null;

        UserFullname userFullname = new UserFullname();
        String firstPart = !parts[0].isEmpty() ? parts[0] : null;
        if(s.contains(SEPARATOR)){
            userFullname.setSurname(firstPart);

            if(parts.length >= 2 && parts[1] != null
            && !parts[1].isEmpty())
                userFullname.setName(parts[1]);
        } else{
            userFullname.setName(firstPart);
        }

        return userFullname;
    }
}
