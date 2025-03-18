<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Application</title>
    <link rel="stylesheet" type="text/css" href="http://localhost/static/form/style.css">
    <script defer src="http://localhost/static/form/script.js"></script>
</head>
<body>
<div class="container">
    <button id="open-form">Открыть форму</button>
</div>
<div class="popup">
    <div class="popup-content">
        <h3>Форма для розыгрыша</h3><br>

        <form id="form" action="" method="POST">
            <table>
                <tr>
                    <td><label for="fullname">ФИО</label></td>
                    <td><input name="fullname" id="fullname" type="text" required></td>
                </tr>
                <tr>
                    <td><label for="email">Email</label></td>
                    <td><input name="email" id="email" type="email" required></td>
                </tr>
                <tr>
                    <td><label for="phone">Телефон</label></td>
                    <td><input id="phone" name="phone" type="tel" required></td>
                </tr>
                <tr>
                    <td><label for="birthday">Дата рождения</label></td>
                    <td><input id="birthday" name="birthday" type="date" required></td>
                </tr>

                <tr>
                    <td>
                        <label>Пол</label>
                    </td>
                    <td>
                        <div>
                            <input type="radio" checked="checked" required name="gender" value="male" id="male"/>
                            <label class="form-check-label" for="male">Мужской</label>
                        </div>

                        <div>
                            <input type="radio" name="gender" required value="female" id="female" />
                            <label for="female">Женский</label>
                        </div>
                    </td>
                </tr>


                <tr>
                    <td>
                        <label for="languages">Укажите любимые языки:</label>
                    </td>
                    <td>
                        <select id="languages" name="languages" multiple="multiple" required>
                            <option value="Pascal">Pascal</option>
                            <option value="C">C</option>
                            <option value="C++">C++</option>
                            <option value="JavaScript">JavaScript</option>
                            <option value="PHP">PHP</option>
                            <option value="Python">Python</option>
                            <option value="Java">Java</option>
                            <option value="Haskel">Haskel</option>
                            <option value="Clojure">Clojure</option>
                            <option value="Prolog">Prolog</option>
                            <option value="Scala">Scala</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="biography">Биография</label></td>
                    <td><textarea id="biography" name="biography" placeholder="Я родился в Москве в 70-м на краю города"></textarea></td>
                </tr>
            </table>

            <label>
                <input type="checkbox" name="checkbox">
                Готов стать миллионером
            </label>
            <br>
            <div id="button-container">
                <button type="submit">Отправить</button>
                <button id="close-form" type="button">Закрыть форму
                    (не хочу быть богатым)
                </button>
            </div>
            <div id="result"></div>
        </form>
    </div>
</div>
</body>
</html>
