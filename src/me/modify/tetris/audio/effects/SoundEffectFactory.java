package me.modify.tetris.audio.effects;

/**
 * Factory pattern object which creates the associated sound effect or returns the error sound effect if
 * type is not recognised.
 */
public class SoundEffectFactory {

    public static SoundEffect createSoundEffect(Effect type) {
        switch (type) {
            case MOVE -> {
                return new MoveSoundEffect();
            }

            case GAME_OVER -> {
                return new GameOverSoundEffect();
            }

            case ROW_CLEAR -> {
                return new RowClearSoundEffect();
            }

            case LEVEL_UP -> {
                return new LevelUpSoundEffect();
            }

            default -> {
                return new ErrorSoundEffect();
            }
        }
    }
}
