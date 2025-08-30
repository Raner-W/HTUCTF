<template>
  <!-- 布局：复用侧边栏布局 -->
  <IndexSidebarLayout>
    <!-- 页面容器：居中卡片 -->
    <div class="forget-password-page">
      <!-- 复用认证卡片容器 -->
      <ForgetPasswdCardComponent>
        <!-- 复用标题区：传递忘记密码专属文案 -->
        <ForgetPasswdHeaderComponent
            title="忘记密码"
            desc="重置你的账户密码，继续使用HTUCTF平台服务"
        />

        <!-- 表单：仅保留提交逻辑，输入组全复用组件 -->
        <form @submit.prevent="handlePasswordReset" novalidate>
          <!-- 复用错误提示：绑定错误信息 -->
          <ForgetPasswdErrorTipComponent :message="errorMsg" />

          <!-- 1. 邮箱输入：复用FormGroup -->
          <ForgetPasswdFormGroupComponent
              label="注册邮箱"
              id="email"
              type="email"
              placeholder="请输入注册时绑定的邮箱"
              v-model="formData.email"
              required
              :onBlur="validateEmail"
          />

          <!-- 2. 验证码输入：复用CodeInputGroup -->
          <ForgetPasswdCodeInputGroupComponent
              label="验证码"
              id="verificationCode"
              placeholder="请输入收到的验证码"
              v-model="formData.code"
              :btnText="codeBtnText"
              :isBtnDisabled="isCodeBtnDisabled"
              required
              :onBtnClick="getVerificationCode"
          />

          <!-- 3. 新密码输入：复用FormGroup -->
          <ForgetPasswdFormGroupComponent
              label="新密码"
              id="newPassword"
              type="password"
              placeholder="请输入6-20位字符（含字母/数字）"
              v-model="formData.newPassword"
              required
              :onBlur="validatePassword"
          />

          <!-- 提交按钮：复用AuthButton，绑定加载状态 -->
          <ForgetPasswdButtonComponent
              text="确认重置密码"
              loadingText="重置中..."
              :isLoading="isSubmitting"
              :disabled="isSubmitting"
              :onClick="handlePasswordReset"
          />
        </form>

        <!-- 底部跳转：复用AuthFooter，传递登录链接 -->
        <ForgetPasswdFooterComponent
            text="记得密码了？"
            linkText="立即登录"
            linkHref="/login"
        />
      </ForgetPasswdCardComponent>
    </div>
  </IndexSidebarLayout>
</template>

<script setup>
// 仅引入必要的布局和组件（无样式文件）
import IndexSidebarLayout from "@/components/ui/layouts/indexSidebarLayout/indexSidebarLayout.vue";
import ForgetPasswdCardComponent from "@/components/ui/components/forgetPasswordComponent/forgetPasswdCardComponent.vue";
import ForgetPasswdHeaderComponent from "@/components/ui/components/forgetPasswordComponent/forgetPasswdHeaderComponent.vue";
import ForgetPasswdFooterComponent from "@/components/ui/components/forgetPasswordComponent/forgetPasswdFooterComponent.vue";
import ForgetPasswdFormGroupComponent from "@/components/ui/components/forgetPasswordComponent/forgetPasswdFormGroupComponent.vue";
import ForgetPasswdCodeInputGroupComponent from "@/components/ui/components/forgetPasswordComponent/forgetPasswdCodeInputGroupComponent.vue";
import ForgetPasswdButtonComponent from "@/components/ui/components/forgetPasswordComponent/forgetPasswdButtonComponent.vue";
import ForgetPasswdErrorTipComponent from "@/components/ui/components/forgetPasswordComponent/forgetPasswdErrorTipComponent.vue";

// 仅保留业务核心逻辑（无UI逻辑）
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

// 1. 表单业务数据（仅存储必要字段）
const formData = ref({
  email: "",
  code: "",
  newPassword: ""
});

// 2. 交互状态（仅控制组件显示）
const errorMsg = ref("");
const isCodeBtnDisabled = ref(false);
const codeBtnText = ref("获取验证码");
const countdown = ref(0);
const isSubmitting = ref(false);

// 3. 表单验证（仅业务规则，无UI渲染）
const validateEmail = () => {
  const emailReg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  if (formData.value.email && !emailReg.test(formData.value.email)) {
    errorMsg.value = "请输入有效的邮箱地址（例：xxx@xx.com）";
    return false;
  }
  errorMsg.value = "";
  return true;
};

const validatePassword = () => {
  const pwdReg = /^.{6,20}$/;
  if (formData.value.newPassword && !pwdReg.test(formData.value.newPassword)) {
    errorMsg.value = "新密码需为6-20位字符，请重新输入";
    return false;
  }
  errorMsg.value = "";
  return true;
};

// 4. 验证码业务逻辑（仅接口交互，无按钮样式）
const getVerificationCode = () => {
  if (!formData.value.email) {
    errorMsg.value = "请先输入注册邮箱";
    return;
  }
  if (!validateEmail()) return;

  // 模拟接口请求（实际项目替换为真实接口）
  console.log("向邮箱发送验证码:", formData.value.email);
  errorMsg.value = "验证码已发送，请注意查收（有效期5分钟）";

  // 倒计时逻辑（仅控制组件状态）
  countdown.value = 60;
  isCodeBtnDisabled.value = true;
  codeBtnText.value = `${countdown.value}秒后重新获取`;

  const timer = setInterval(() => {
    countdown.value--;
    codeBtnText.value = `${countdown.value}秒后重新获取`;
    if (countdown.value <= 0) {
      clearInterval(timer);
      codeBtnText.value = "获取验证码";
      isCodeBtnDisabled.value = false;
    }
  }, 1000);
};

// 5. 密码重置核心逻辑（仅接口交互，无提交按钮样式）
const handlePasswordReset = async () => {
  // 全量校验（仅业务规则）
  if (!formData.value.email || !formData.value.code || !formData.value.newPassword) {
    errorMsg.value = "请完善所有必填项后提交";
    return;
  }
  if (!validateEmail() || !validatePassword()) return;

  // 锁定状态（仅控制组件禁用）
  isSubmitting.value = true;
  errorMsg.value = "";

  try {
    // 模拟接口请求（实际项目替换为真实接口）
    await new Promise(resolve => setTimeout(resolve, 1500));
    console.log("提交密码重置请求:", formData.value);

    // 成功逻辑（仅路由跳转和状态提示）
    errorMsg.value = "密码重置成功！即将跳转登录页...";
    setTimeout(() => router.push("/login"), 1500);
  } catch (err) {
    // 异常处理（仅提示信息）
    errorMsg.value = err.message || "密码重置失败，请稍后重试";
  } finally {
    // 释放状态（仅控制组件启用）
    isSubmitting.value = false;
  }
};
</script>

<style scoped>
/* 仅保留页面级居中样式（无组件样式） */
.forget-password-page {
  width: 100%;
  min-height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px 20px;
  box-sizing: border-box;

  /* 响应式适配（仅页面容器） */
  @media (max-width: 1024px) {
    padding: 20px 16px;
  }
}
</style>