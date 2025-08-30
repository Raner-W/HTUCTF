<template>
  <div class="form-group">
    <label :for="id">{{ label }}</label>
    <input
        :id="id"
        :type="type"
        :placeholder="placeholder"
        class="custom-input"
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
        :required="required"
        @blur="onBlur"
    />
  </div>
</template>

<script setup>
const props = defineProps({
  label: { type: String, required: true }, // 输入框标签
  id: { type: String, required: true },   // 输入框ID（关联label）
  type: { type: String, default: "text" },// 输入框类型（text/email/password）
  placeholder: { type: String, default: "" },// 占位提示
  modelValue: { type: String, default: "" },// 双向绑定值（v-model）
  required: { type: Boolean, default: false },// 是否必填
  onBlur: { type: Function, default: () => {} }// 失焦回调（用于验证）
});

// 支持v-model语法糖（触发父组件更新）
defineEmits(["update:modelValue"]);
</script>

<style scoped>
/* 与登录页form-group样式完全对齐 */
.form-group {
  margin-bottom: 20px;
}
.form-group label {
  display: block;
  margin-bottom: 8px;
  font-size: 14px;
  color: #1a202c;
  font-weight: 500;
}
/* 与登录页custom-input样式完全对齐 */
.custom-input {
  width: 100%;
  height: 48px;
  padding: 0 16px;
  border: 1px solid #e2e8f0;
  border-radius: 8px;
  font-size: 16px;
  color: #1a202c;
  box-sizing: border-box;
  transition: border-color 0.3s ease;
}
.custom-input:focus {
  outline: none;
  border-color: #4299e1;
  box-shadow: 0 0 0 3px rgba(66, 153, 225, 0.1);
}
</style>