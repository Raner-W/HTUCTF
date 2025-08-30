<template>
  <div class="code-input-group">
    <!-- 复用FormGroup做输入框 -->
    <ForgetPasswdFormGroupComponent
        :label="label"
        :id="id"
        type="text"
        :placeholder="placeholder"
        :modelValue="modelValue"
        @update:modelValue="$emit('update:modelValue', $event)"
        :required="required"
        :onBlur="onBlur"
    />

    <!-- 复用AuthButton做获取验证码按钮 -->
    <ForgetPasswdButtonComponent
        :text="btnText"
        :disabled="isBtnDisabled"
        :onClick="onBtnClick"
        :loadingText="btnLoadingText"
        :isLoading="isBtnLoading"
    />
  </div>
</template>

<script setup>
import ForgetPasswdFormGroupComponent from "./forgetPasswdFormGroupComponent.vue";
import ForgetPasswdButtonComponent from "./forgetPasswdButtonComponent.vue";

const props = defineProps({
  label: { type: String, required: true }, // 验证码标签
  id: { type: String, required: true },   // 输入框ID
  placeholder: { type: String, default: "请输入验证码" },// 占位提示
  modelValue: { type: String, default: "" },// 验证码值（v-model）
  btnText: { type: String, default: "获取验证码" },// 按钮文本
  btnLoadingText: { type: String, default: "发送中..." },// 按钮加载文本
  isBtnDisabled: { type: Boolean, default: false },// 按钮是否禁用
  isBtnLoading: { type: Boolean, default: false },// 按钮是否加载中
  required: { type: Boolean, default: false },// 是否必填
  onBlur: { type: Function, default: () => {} },// 失焦回调
  onBtnClick: { type: Function, default: () => {} }// 按钮点击回调
});

// 支持v-model
defineEmits(["update:modelValue"]);
</script>

<style scoped>
.code-input-group {
  display: flex;
  gap: 12px;
  margin-bottom: 20px;
}
/* 输入框占主要宽度，按钮固定宽度 */
.code-input-group .form-group {
  flex: 1;
  margin-bottom: 0;
}
.code-input-group .auth-btn {
  width: 140px;
  height: 48px; /* 与输入框高度对齐 */
}

/* 响应式适配 */
@media (max-width: 1024px) {
  .code-input-group .auth-btn {
    width: 120px;
  }
}
</style>