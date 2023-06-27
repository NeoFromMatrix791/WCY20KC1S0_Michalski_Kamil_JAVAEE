export class DateUtil {

    public static format(date?: string) {
        if (!date) {
            return '';
        }

        const dateObject: Date = new Date(date);
        const year: string = dateObject.getFullYear() + '';
        const month: string = DateUtil.pad(dateObject.getMonth() + 1 + '');
        const day: string = DateUtil.pad(dateObject.getDate() + '');

        const hour: string = DateUtil.pad(dateObject.getHours() + '');
        const minute: string = DateUtil.pad(dateObject.getMinutes() + '');
        const second: string = DateUtil.pad(dateObject.getSeconds() + '');

        return `${day}.${month}.${year} ${hour}:${minute}:${second}`;
    }

    private static pad(value: string): string {
        return value.padStart(2, '0');
    }
}