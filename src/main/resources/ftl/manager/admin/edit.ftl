<@managerPage menuBigItem="ADMIN_USER_EDIT"
css=["/css/lib/bootstrapValidator.min.css"]
scripts=["/js/bootstrapValidator.min.js", "/js/bootstrapValidator.zh_CN.js", "/js/jquery.form.js"]>

<div class="row col-md-12">
    <h4 class="title">修改登录信息</h4>
</div>

<div class="row col-md-12 form">
    <div id="alertTips" class="alert display-none" role="alert"></div>
    <form id="adminUserEditForm" class="form-horizontal" action="/manager/admin/save">
        <div class="form-group">
            <label for="userName" class="col-sm-2 control-label">用户名</label>

            <div class="col-sm-4">
                <input type="text" class="form-control" id="userName" name="userName" placeholder="请输入用户名"
                       value="${(adminUser.userName)!}">
            </div>
        </div>
        <div class="form-group">
            <label for="oldPassword" class="col-sm-2 control-label">当前密码</label>

            <div class="col-sm-4">
                <input type="password" class="form-control" id="oldPassword" name="oldPassword"
                       placeholder="请输入当前正在使用的密码">
            </div>
        </div>
        <div class="form-group">
            <label for="newPassword" class="col-sm-2 control-label">新密码</label>

            <div class="col-sm-4">
                <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="请输入新密码">
            </div>
        </div>
        <div class="form-group">
            <label for="verifyPassword" class="col-sm-2 control-label">重复新密码</label>

            <div class="col-sm-4">
                <input type="password" class="form-control" id="verifyPassword" name="verifyPassword"
                       placeholder="请再次输入新密码">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button id="loginBtn" type="button" class="btn btn-primary">保&nbsp;&nbsp;存</button>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
    $(function () {
        $('#adminUserEditForm').ajaxForm();

        $('#adminUserEditForm').bootstrapValidator({
            err: {
                container: 'tooltip'
            },
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
                oldPassword: {
                    validators: {
                        notEmpty: {message: '请输入密码'},
                        stringLength: {min: 6, max:30}
                    }
                },
                newPassword: {
                    validators: {
                        notEmpty: {message: '请输入密码'},
                        stringLength: {min: 6, max:30}
                    }
                },
                verifyPassword: {
                    validators: {
                        notEmpty: {message: '两次密码输入不一致'},
                        identical: {
                            field: 'newPassword',
                            message: '两次密码输入不一致'
                        }
                    }
                }
            }
        }).on('success.form.bv', function (e, data) {
            e.preventDefault();
            $('#adminUserEditForm').ajaxSubmit({
                dataType: 'json',
                success: function (data) {
                    var addclass = (data.code == 1) ? "alert-success" : "alert-danger";
                    var removeclass = (data.code == 1) ? "alert-danger" : "alert-success";
                    $("#alertTips").html(data.message).removeClass(removeclass).addClass(addclass).slideDown("slow");
                    window.setTimeout(function () {
                        $("#alertTips").slideUp("slow");
                        if (data.code == 1) {
                            window.location = "/manager/login";
                        }
                    }, 2000);
                }
            });
        }).on("init.field.fv", function (e, data) {
            console.info("---");
        });

        $("#loginBtn").click(function () {
            $('#adminUserEditForm').bootstrapValidator('validate');
        });
    });
</script>
</@managerPage>