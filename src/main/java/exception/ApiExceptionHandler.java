//package exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@ControllerAdvice
//public class ApiExceptionHandler {
//
//    @ExceptionHandler(value = {SkillNotFoundException.class})
//    public ResponseEntity<Object> handleSkillNotFoundException
//            (SkillNotFoundException e)
//    {
//        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
//        SkillException skillException = new SkillException(
//                e.getMessage(),
//                e.getCause(),
//                httpStatus
//        );
//
//        return new ResponseEntity<>(skillException, httpStatus);
//    }
//
////    @ExceptionHandler(value = {CvNotFoundException.class})
////    public ResponseEntity<Object> handleSkillNotFoundException
////            (CvNotFoundException cvNotFoundException)
////    {
////        CvException cvException = new CvException(
////                cvNotFoundException.getMessage(),
////                cvNotFoundException.getCause(),
////                HttpStatus.NOT_FOUND
////        );
////
////        return new ResponseEntity<>(cvException, HttpStatus.NOT_FOUND);
////    }
//
//
//
////    @ExceptionHandler(value = {LevelSkillNotFoundException.class})
////    public ResponseEntity<Object> handleSkillNotFoundException
////            (LevelSkillNotFoundException levelSkillNotFoundException)
////    {
////        LevelSkillException levelSkillException = new LevelSkillException(
////                levelSkillNotFoundException.getMessage(),
////                levelSkillNotFoundException.getCause(),
////                HttpStatus.NOT_FOUND
////        );
////
////        return new ResponseEntity<>(levelSkillException, HttpStatus.NOT_FOUND);
////    }
//
//}
