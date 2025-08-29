import { createApp } from 'vue'
import { createPinia } from 'pinia' // 导入Pinia
import App from './App.vue'
import router from './router'

// 创建应用实例
const app = createApp(App)

// 创建Pinia实例并使用
const pinia = createPinia()
app.use(pinia)
app.use(router)

app.mount('#app')