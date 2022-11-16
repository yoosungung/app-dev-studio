export default class TextUtil {
    private static readonly UNICODE_CHAR_SIZE: number = 3;

    public static getTotalByte(value: string | undefined, unicodeCharSize?: number): number {
        if (!value || value.length == 0) {
            return 0;
        }

        let totByte: number = 0;

        for (let i = 0; i < value.length; i++) {
            totByte += (value.charCodeAt(i) <= 128 ? 1 : (unicodeCharSize || TextUtil.UNICODE_CHAR_SIZE));
        }

        return totByte;
    }

    public static getMaxByteLength(value: string | undefined, maxByte: number, unicodeCharSize?: number): number {
        if (!value || value.length == 0) {
            return maxByte;
        }

        let totByte: number = TextUtil.getTotalByte(value, unicodeCharSize);

        return Math.max(maxByte - (totByte - value.length), 0);
    }

    public static getTruncatedValue(value: string, maxByte: number | undefined, unicodeCharSize?: number) {
        if (!maxByte) {
            return value;
        }

        let totByte: number = 0;
        let idx = 0;

        for (let i = 0; i < value.length; i++) {
            totByte += (value.charCodeAt(i) <= 128 ? 1 : (unicodeCharSize || TextUtil.UNICODE_CHAR_SIZE));

            if (totByte <= maxByte) {
                idx = i;
            }
        }

        value = value.substring(0, idx + 1);

        if (value.substr(value.length - 1) == "\r") {
            value = value.substr(0, value.length - 1);
        }

        return value;
    }
}
