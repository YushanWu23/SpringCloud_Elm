import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from "unplugin-auto-import/vite";
import ReactivityTransform from '@vue-macros/reactivity-transform/vite';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),ReactivityTransform(), // 启用响应式语法糖 $ref ...
     AutoImport({
      imports: ["vue", "vue-router"],
    }),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
