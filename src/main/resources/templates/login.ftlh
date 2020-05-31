<!doctype html>
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign in</title>
    <style>
        html {
            height: 100%;
        }

        body {
            height: 100%;
            display: flex;
            align-items: center;
        }

        .login-row {
            align-items: center;
        }

        .login-img {
            max-width: 300px;
        }

        .btns-wrapper {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row login-row">
        <div class="col">
            <h1><@spring.message 'signIn.main.word'/></h1>
            <form action="/signIn" method="post">
                <div class="form-group">
                    <label for="email"><h7><@spring.message 'signIn.email'/></h7></label>
                    <input type="text" id="email" name="email" placeholder="write email.." class="form-control">
                </div>
                <div class="form-group">
                    <label for="password"><h7><@spring.message 'signIn.password'/></h7></label>
                    <input type="password" id="password" name="password" placeholder="write password.."
                           class="form-control">
                </div>
                <div class="btns-wrapper">
                    <button type="submit" class="btn btn-dark"><h7><@spring.message 'signIn.button.login'/></h7></button>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <p></p>
                <label>
                    <input type="checkbox" name="remember-me">Запомни меня
                </label>
            </form>
            <a href="/signIn?lang=ru">
                <h7><@spring.message 'signIn.changeLangRu'/></h7>
            </a>
            <p></p>
            <a href="/signIn?lang=en">
                <h7><@spring.message 'signIn.changeLangEng'/></h7>
            </a>
        </div>
    </div>
</div>


</body>
</html>