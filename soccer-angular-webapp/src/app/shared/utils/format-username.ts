/**
 * 格式化更新人姓名
 */
export function formatUsername(value: string): string {
  if (value == null) {
    return value;
  }
  if (!value.includes('~~~')) {
    return value;
  }
  return value.split('~~~')[1];
}
