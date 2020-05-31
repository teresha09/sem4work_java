<!doctype html>
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up</title>
    <style>
        html {
            height: 100%;
        }

        body {
            height: 100%;
            display: flex;
            align-items: center;
        }

        .registration-row {
            align-items: center;
        }

        .registration-img {
            max-width: 300px;
        }

        .btns-wrapper {
            display: flex;
            justify-content: space-between;
        }

        .error {
            color: #ff0000;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row registration-row">
        <div class="col">
            <h1><@spring.message 'signUp.main.word'/></h1>
            <@spring.bind "SignUpDto"/>
            <form action="/registration" method="POST">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="form-group">
                    <br>
                    <h4><@spring.message 'signUp.name'/></h4>
                    <@spring.formInput "SignUpDto.name"/>
                    <@spring.showErrors "<br>"/>
                    <br><br>
                </div>
                <div class="form-group">
                    <br>
                    <h4><@spring.message 'signUp.email'/></h4>
                    <@spring.formInput "SignUpDto.email"/>
                    <@spring.showErrors "<br>"/>
                    <br><br>
                </div>
                <div class="form-group">
                    <label for="password">
                        <h4><@spring.message 'signUp.password'/></h4>
                        <@spring.formInput "SignUpDto.password"/>
                        <@spring.showErrors "<br>","error"/>
                    </label>
                </div>
                <div class="btns-wrapper">
                    <button type="submit" class="btn btn-dark">
                       <@spring.message 'signUp.button.signUp'/>
                    </button>
                </div>
            </form>
            <p></p>
            <a href="/registration?lang=ru">
                <h7><@spring.message 'signUp.button.changeLangToRus'/></h7>
            </a>
            <p></p>
            <a href="/registration?lang=en">
                <h7><@spring.message 'signUp.button.changeLangToEng'/></h7>
            </a>
        </div>
    </div>
</div>



</body>
</html>