export function transferTime(time: string) {
    let createTime: any = new Date(time)

    let now: any = new Date()

    let diff: number = now - createTime

    let result;

    let diffMinutes = Math.round(diff / 60000)
    if (diffMinutes < 1) {
        result = '几秒前'
    }

    if (diffMinutes < 60 && diffMinutes >= 1) {
        result = `${diffMinutes}分钟前`
    }

    let diffHours = Math.round(diffMinutes / 60)
    if (diffHours < 24 && diffHours >= 1) {
        result = `${diffHours}小时前`
    }

    let diffDay = Math.round(diffHours / 24)
    if (diffDay < 30 && diffDay >= 1) {
        result = `${diffDay}天前`
    }

    let diffMonths = Math.round(diffDay / 30)
    if (diffMonths < 12 && diffMonths >= 1) {
        result = `${diffMonths}月前`
    }

    let diffYears = Math.round(diffDay / 360)
    if (diffYears >= 1) {
        result = `${diffYears}年前`
    }


    return result



}