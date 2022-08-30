
package fr.m2i.javaspringmvc.controller;

import fr.m2i.javaspringmvc.form.StudentForm;
import fr.m2i.javaspringmvc.model.Student;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

//    @GetMapping("/student")
//    public ModelAndView showStudentForm() {
//        return new ModelAndView("student", "command", new Student()); 
//        // form spring mvc a besoin d'un bean (class),
//        // 1 er param c'est la vue, 2emme parm: mot clé command dans lequelle l'objet du form sera mis
//        // pas de modelAttribute="studentForm" non defini dans la form jsp
//        // s'il ya plusieurs form dans la page, ou on veut recuperer une partie de données du formaulaire
//        //on est obligé d'utiliser modelAttribute="studentForm" dans le form spring mvc du fichier jsp (au lieu command)
//    }
//    
//    
//    
//     @PostMapping("/addStudent")
//    public String addStudent(@ModelAttribute Student student, ModelMap model) {
//        model.addAttribute("id", student.getId());
//        model.addAttribute("age", student.getAge());
//        model.addAttribute("name", student.getName());
//
//        return "result";
//    }
    
/* ********************************************************** */
    
/*
     * 1ere solution -> notre méthode prend en paramètre le model pour pouvoir
     *                 SET notre bean StudentForm pour le bon fonctionnement de notre form côté JSP
     *                 la méthode doit retourner une String : le nom de la vue qu'on souhaite afficher
     *
     *                 /!\ DANS CHAQUE METHODE GET ou POST JE VAIS DEVOIR RENSEIGNER L'ATTRIBUT "studentForm" /!\
     *                 /!\ model.addAttribute("studentForm", new StudentForm()); /!\
     */
//    @GetMapping("/student")
//    public String showStudentForm(ModelMap model) {
//        model.addAttribute("studentForm", new StudentForm());
//        return "student";
//    }

    /*
     * 2eme solution -> notre méthode travaille avec ModelAndView on doit spécifier dans l'ordre
     *                 le nom de la vue qu'on veut afficher, le nom du modelAttribute utiliser par le formulaire
     *                 la valeur de l'attribut
     *
     *                 /!\ DANS CHAQUE METHODE GET ou POST JE VAIS DEVOIR RENSEIGNER L'ATTRIBUT "studentForm" /!\
     *                 /!\ model.addAttribute("studentForm", new StudentForm()); /!\
     */
//    @GetMapping("/student")
//    public ModelAndView showStudentForm() {
//        return new ModelAndView("student", "studentForm", new StudentForm());
//    }

    
    /*
    utiliser une classe form qui contient seulement les données recu du front
    --> separer le model de la partie echangé avec le front (c'est les DTO)
    et qui permet d'ajouter les annoation de validation des données en provenance de front
    Classe studentForm ne contient que le nom et l'age.
    
    */
   
     /*
     * 3eme solution -> On travaille en injection de dépendance avec la méthode 'addStudentForm' 
     *                 donc notre GET a juste besoin de retourner le de la vue a afficher
     */
    @GetMapping("/student")
    public String showStudentForm() {
        return "studentForm";
    }

//    @PostMapping("/addStudent") // sans validation
//    public String addStudent(@ModelAttribute("studentForm") StudentForm student, ModelMap model) {
//        model.addAttribute("id", 1);
//        model.addAttribute("age", student.getAge());
//        model.addAttribute("name", student.getName());
//
//        return "result";
//    }
    
    @PostMapping("/addStudent")
    public String addStudent(@ModelAttribute @Valid StudentForm student,
            BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            return "studentForm";
        }

        model.addAttribute("id", 1);
        model.addAttribute("age", student.getAge());
        model.addAttribute("name", student.getName());

        return "result";
    }

    // 3 eme solution -> injection de dépendance
    @ModelAttribute("studentForm")
    public StudentForm addStudentForm() {
        return new StudentForm();
    }
    

}