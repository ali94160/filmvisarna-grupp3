<template>
  <div>
    <a class="waves-effect waves-light btn">button</a>
    <a class="waves-effect waves-light btn"
      ><i class="material-icons left">cloud</i>button</a
    >
    <a class="waves-effect waves-light btn"
      ><i class="material-icons right">cloud</i>button</a
    >
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data() {
    return {
      count: 0
    }
  }
}
</script>
