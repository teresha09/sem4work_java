<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>to_confirm</title>
    <style>
        a.profile-dropdown-toggle {
            color: white;
        }

        .container-post {
            margin-top: 30px;
            margin-bottom: 30px;
        }

        .card-post {
            padding: 16px;
        }

        .card-like-button-wrap {
            display: flex;
            justify-content: flex-end;
        }

        .card-like-button {
            margin-left: 12px;
            padding: 0;
            width: 30px;
            background: transparent;
            border: 0;
        }

        .card-like-icon {
            max-width: 24px;
            height: auto;
        }

        .media-author {
            padding: 16px 0;
        }

        .media-author-photo {
            max-width: 80px;
        }

        .media-author-body {
            color: black;
            font-size: small;
        }

        .card-text {
            color: black;
            font-size: 14px;
        }

        .card a:hover {
            text-decoration: none;
        }
    </style>
</head>
<body>

<div class="container container-post">
    <div class="row">
        <div class="col">
            <#if isConfirmed == true>
                <h1>Подтверждено!</h1>
            <#else>
                <h1>ПАПУТАЛ ?</h1>
            </#if>
        </div>
    </div>
    <div class="btns-wrapper">
        <a href="/signIn">
            <button type="submit" class="btn btn-dark">Вход</button>
        </a>
    </div>
</div>

</body>
</html>