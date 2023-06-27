package pl.edu.wat.util;

import lombok.experimental.UtilityClass;
import pl.edu.wat.model.HashDTO;

@UtilityClass
public class AssertHashModifiers {

    public static void arePropertiesValid(final HashDTO dto) {
        final String hash128Value = dto.getHash128().trim();
        final String hash256Value = dto.getHash256().trim();
        final String hash512Value = dto.getHash512().trim();

        boolean result = isNotEmpty(hash128Value) && isNotEmpty(hash256Value) && isNotEmpty(hash512Value);
        if (!result) {
            throw new IllegalArgumentException("Properties must contain value");
        }
    }

    private static boolean isNotEmpty(final String string) {
        return !string.trim().isEmpty();
    }
}
