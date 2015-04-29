<@managerPage showMenu="FALSE" showNav="FALSE"
css=["/css/compiled/signin.css", "/css/lib/bootstrapValidator.min.css"]
scripts=["/js/bootstrapValidator.min.js", "/js/bootstrapValidator.zh_CN.js", "/js/jquery.form.js"]>
<div class="login-wrapper">
    <div class="box">
        <div class="content-wrap">
            <h6>管理登录</h6>

            <div id="alertTips" class="alert alert-warning hide" role="alert"></div>

            <form id="login-form" class="form-horizontal" method="post" action="/manager/loginAjax">
                <div class="form-group">
                    <div class="col-sm-11">
                        <input id="userName" name="userName" class="form-control validate[required]" type="text"
                               placeholder="输入用户名" maxlength="30">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-11">
                        <input id="password" name="password" class="form-control validate[required]" type="password"
                               placeholder="输入登录密码" maxlength="20">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-5 clear-padding-right">
                        <input id="verifyCode" name="verifyCode" class="form-control validate[required]" type="text"
                               placeholder="输入验证码" maxlength="4">
                    </div>
                    <div class="col-sm-3">
                        <img class="captcha" src="/captcha.png" alt="验证码"/>
                    </div>
                    <div class="col-sm-3 clear-padding-left">
                        <a class="captcha-refesh-btn" href="javascript:;">看不清刷一刷?</a>
                    </div>
                </div>
                <a id="login-btn" class="btn-glow primary login row-margin-top" href="javascript:;">登录</a>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $('#login-form').ajaxForm();

        $('#login-form').bootstrapValidator({
            err: {container: 'tooltip'},
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            locale: 'zh_CN',
            fields: {
                userName: {
                    validators: {
                        notEmpty: {message: '请输入用户名'},
                        regexp: {
                            regexp: /^[a-zA-Z0-9]{6,30}$/,
                            message: '用户名为6-30个由英文、数字构成'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {message: '请输入密码'},
                        stringLength: {min: 6, max: 30}
                    }
                },
                verifyCode: {
                    validators: {
                        notEmpty: {message: '请输入验证码'},
                        regexp: {regexp: /^[a-zA-Z0-9]{4}$/}
                    }
                }
            }
        }).on('success.form.bv', function (e, data) {
            e.preventDefault();
            $('#login-form').ajaxSubmit({
                dataType: 'json',
                success: function (data) {
                    if (data.code == 1) {
                        window.location = "/manager/user";
                    } else {
                        $("#alertTips").html(data.message).slideDown("slow").removeClass("hide");
                        window.setTimeout(function () {$("#alertTips").slideUp("slow")}, 1000);
                    }
                }
            });
        });

        $("#login-btn").click(function () {
            $('#login-form').bootstrapValidator('validate');
        });

        $(".captcha,.captcha-refesh-btn").click(function () {
            $(".captcha").prop("src", '/captcha.png?' + new Date().getTime());
        });
    });
</script>
</@managerPage>