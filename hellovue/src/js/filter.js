const fieldFormat=function(val, format) {
  if (typeof (format) === 'function') {
    return format(val)
  } else return val
}

export default {
  fieldFormat
}
