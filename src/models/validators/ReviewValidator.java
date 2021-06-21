package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Review;

public class ReviewValidator {
    public static List<String> validate(Review r) {
        List<String> errors = new ArrayList<String>();

        String name_error = validateName(r.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String title_error = validateTitle(r.getTitle());
        if(!title_error.equals("")) {
            errors.add(title_error);
        }

        String impression_error = validateImpression(r.getImpression());
        if(!impression_error.equals("")) {
            errors.add(impression_error);
        }

        return errors;
    }

        private static String validateName(String name) {
            if(name == null || name.equals("")) {
                return "名前を入力してください。";
            }

            return "";
        }

        private static String validateTitle(String title) {
            if(title == null || title.equals("")) {
                return "タイトルを入力してください。";
            }

            return "";
        }

        private static String validateImpression(String impression) {
            if(impression == null || impression.equals("")) {
                return "感想を入力してください。";
            }

            return "";
        }
}
