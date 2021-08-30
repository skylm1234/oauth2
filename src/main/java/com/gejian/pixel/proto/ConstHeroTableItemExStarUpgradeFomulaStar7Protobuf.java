// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ConstHeroTableItemExStarUpgradeFomulaStar7.proto

package com.gejian.pixel.proto;

public final class ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf {
  private ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ConstHeroTableItemExStarUpgradeFomulaStar7OrBuilder extends
      // @@protoc_insertion_point(interface_extends:ConstHeroTableItemExStarUpgradeFomulaStar7)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>uint32 hp = 1;</code>
     */
    int getHp();

    /**
     * <code>uint32 attack = 2;</code>
     */
    int getAttack();

    /**
     * <code>uint32 defense = 3;</code>
     */
    int getDefense();

    /**
     * <code>uint32 speed = 4;</code>
     */
    int getSpeed();

    /**
     * <code>uint32 hp_upgrade = 5;</code>
     */
    int getHpUpgrade();

    /**
     * <code>uint32 attack_upgrade = 6;</code>
     */
    int getAttackUpgrade();

    /**
     * <code>uint32 defense_upgrade = 7;</code>
     */
    int getDefenseUpgrade();

    /**
     * <code>uint32 speed_upgrade = 8;</code>
     */
    int getSpeedUpgrade();
  }
  /**
   * Protobuf type {@code ConstHeroTableItemExStarUpgradeFomulaStar7}
   */
  public  static final class ConstHeroTableItemExStarUpgradeFomulaStar7 extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ConstHeroTableItemExStarUpgradeFomulaStar7)
      ConstHeroTableItemExStarUpgradeFomulaStar7OrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ConstHeroTableItemExStarUpgradeFomulaStar7.newBuilder() to construct.
    private ConstHeroTableItemExStarUpgradeFomulaStar7(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ConstHeroTableItemExStarUpgradeFomulaStar7() {
      hp_ = 0;
      attack_ = 0;
      defense_ = 0;
      speed_ = 0;
      hpUpgrade_ = 0;
      attackUpgrade_ = 0;
      defenseUpgrade_ = 0;
      speedUpgrade_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ConstHeroTableItemExStarUpgradeFomulaStar7(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              hp_ = input.readUInt32();
              break;
            }
            case 16: {

              attack_ = input.readUInt32();
              break;
            }
            case 24: {

              defense_ = input.readUInt32();
              break;
            }
            case 32: {

              speed_ = input.readUInt32();
              break;
            }
            case 40: {

              hpUpgrade_ = input.readUInt32();
              break;
            }
            case 48: {

              attackUpgrade_ = input.readUInt32();
              break;
            }
            case 56: {

              defenseUpgrade_ = input.readUInt32();
              break;
            }
            case 64: {

              speedUpgrade_ = input.readUInt32();
              break;
            }
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.internal_static_ConstHeroTableItemExStarUpgradeFomulaStar7_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.internal_static_ConstHeroTableItemExStarUpgradeFomulaStar7_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7.class, com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7.Builder.class);
    }

    public static final int HP_FIELD_NUMBER = 1;
    private int hp_;
    /**
     * <code>uint32 hp = 1;</code>
     */
    public int getHp() {
      return hp_;
    }

    public static final int ATTACK_FIELD_NUMBER = 2;
    private int attack_;
    /**
     * <code>uint32 attack = 2;</code>
     */
    public int getAttack() {
      return attack_;
    }

    public static final int DEFENSE_FIELD_NUMBER = 3;
    private int defense_;
    /**
     * <code>uint32 defense = 3;</code>
     */
    public int getDefense() {
      return defense_;
    }

    public static final int SPEED_FIELD_NUMBER = 4;
    private int speed_;
    /**
     * <code>uint32 speed = 4;</code>
     */
    public int getSpeed() {
      return speed_;
    }

    public static final int HP_UPGRADE_FIELD_NUMBER = 5;
    private int hpUpgrade_;
    /**
     * <code>uint32 hp_upgrade = 5;</code>
     */
    public int getHpUpgrade() {
      return hpUpgrade_;
    }

    public static final int ATTACK_UPGRADE_FIELD_NUMBER = 6;
    private int attackUpgrade_;
    /**
     * <code>uint32 attack_upgrade = 6;</code>
     */
    public int getAttackUpgrade() {
      return attackUpgrade_;
    }

    public static final int DEFENSE_UPGRADE_FIELD_NUMBER = 7;
    private int defenseUpgrade_;
    /**
     * <code>uint32 defense_upgrade = 7;</code>
     */
    public int getDefenseUpgrade() {
      return defenseUpgrade_;
    }

    public static final int SPEED_UPGRADE_FIELD_NUMBER = 8;
    private int speedUpgrade_;
    /**
     * <code>uint32 speed_upgrade = 8;</code>
     */
    public int getSpeedUpgrade() {
      return speedUpgrade_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (hp_ != 0) {
        output.writeUInt32(1, hp_);
      }
      if (attack_ != 0) {
        output.writeUInt32(2, attack_);
      }
      if (defense_ != 0) {
        output.writeUInt32(3, defense_);
      }
      if (speed_ != 0) {
        output.writeUInt32(4, speed_);
      }
      if (hpUpgrade_ != 0) {
        output.writeUInt32(5, hpUpgrade_);
      }
      if (attackUpgrade_ != 0) {
        output.writeUInt32(6, attackUpgrade_);
      }
      if (defenseUpgrade_ != 0) {
        output.writeUInt32(7, defenseUpgrade_);
      }
      if (speedUpgrade_ != 0) {
        output.writeUInt32(8, speedUpgrade_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (hp_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(1, hp_);
      }
      if (attack_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, attack_);
      }
      if (defense_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(3, defense_);
      }
      if (speed_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(4, speed_);
      }
      if (hpUpgrade_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(5, hpUpgrade_);
      }
      if (attackUpgrade_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(6, attackUpgrade_);
      }
      if (defenseUpgrade_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(7, defenseUpgrade_);
      }
      if (speedUpgrade_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(8, speedUpgrade_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 other = (com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7) obj;

      boolean result = true;
      result = result && (getHp()
          == other.getHp());
      result = result && (getAttack()
          == other.getAttack());
      result = result && (getDefense()
          == other.getDefense());
      result = result && (getSpeed()
          == other.getSpeed());
      result = result && (getHpUpgrade()
          == other.getHpUpgrade());
      result = result && (getAttackUpgrade()
          == other.getAttackUpgrade());
      result = result && (getDefenseUpgrade()
          == other.getDefenseUpgrade());
      result = result && (getSpeedUpgrade()
          == other.getSpeedUpgrade());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + HP_FIELD_NUMBER;
      hash = (53 * hash) + getHp();
      hash = (37 * hash) + ATTACK_FIELD_NUMBER;
      hash = (53 * hash) + getAttack();
      hash = (37 * hash) + DEFENSE_FIELD_NUMBER;
      hash = (53 * hash) + getDefense();
      hash = (37 * hash) + SPEED_FIELD_NUMBER;
      hash = (53 * hash) + getSpeed();
      hash = (37 * hash) + HP_UPGRADE_FIELD_NUMBER;
      hash = (53 * hash) + getHpUpgrade();
      hash = (37 * hash) + ATTACK_UPGRADE_FIELD_NUMBER;
      hash = (53 * hash) + getAttackUpgrade();
      hash = (37 * hash) + DEFENSE_UPGRADE_FIELD_NUMBER;
      hash = (53 * hash) + getDefenseUpgrade();
      hash = (37 * hash) + SPEED_UPGRADE_FIELD_NUMBER;
      hash = (53 * hash) + getSpeedUpgrade();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code ConstHeroTableItemExStarUpgradeFomulaStar7}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ConstHeroTableItemExStarUpgradeFomulaStar7)
        com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7OrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.internal_static_ConstHeroTableItemExStarUpgradeFomulaStar7_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.internal_static_ConstHeroTableItemExStarUpgradeFomulaStar7_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7.class, com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        hp_ = 0;

        attack_ = 0;

        defense_ = 0;

        speed_ = 0;

        hpUpgrade_ = 0;

        attackUpgrade_ = 0;

        defenseUpgrade_ = 0;

        speedUpgrade_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.internal_static_ConstHeroTableItemExStarUpgradeFomulaStar7_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 getDefaultInstanceForType() {
        return com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 build() {
        com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 buildPartial() {
        com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 result = new com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7(this);
        result.hp_ = hp_;
        result.attack_ = attack_;
        result.defense_ = defense_;
        result.speed_ = speed_;
        result.hpUpgrade_ = hpUpgrade_;
        result.attackUpgrade_ = attackUpgrade_;
        result.defenseUpgrade_ = defenseUpgrade_;
        result.speedUpgrade_ = speedUpgrade_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return (Builder) super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7) {
          return mergeFrom((com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 other) {
        if (other == com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7.getDefaultInstance()) return this;
        if (other.getHp() != 0) {
          setHp(other.getHp());
        }
        if (other.getAttack() != 0) {
          setAttack(other.getAttack());
        }
        if (other.getDefense() != 0) {
          setDefense(other.getDefense());
        }
        if (other.getSpeed() != 0) {
          setSpeed(other.getSpeed());
        }
        if (other.getHpUpgrade() != 0) {
          setHpUpgrade(other.getHpUpgrade());
        }
        if (other.getAttackUpgrade() != 0) {
          setAttackUpgrade(other.getAttackUpgrade());
        }
        if (other.getDefenseUpgrade() != 0) {
          setDefenseUpgrade(other.getDefenseUpgrade());
        }
        if (other.getSpeedUpgrade() != 0) {
          setSpeedUpgrade(other.getSpeedUpgrade());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int hp_ ;
      /**
       * <code>uint32 hp = 1;</code>
       */
      public int getHp() {
        return hp_;
      }
      /**
       * <code>uint32 hp = 1;</code>
       */
      public Builder setHp(int value) {
        
        hp_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 hp = 1;</code>
       */
      public Builder clearHp() {
        
        hp_ = 0;
        onChanged();
        return this;
      }

      private int attack_ ;
      /**
       * <code>uint32 attack = 2;</code>
       */
      public int getAttack() {
        return attack_;
      }
      /**
       * <code>uint32 attack = 2;</code>
       */
      public Builder setAttack(int value) {
        
        attack_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 attack = 2;</code>
       */
      public Builder clearAttack() {
        
        attack_ = 0;
        onChanged();
        return this;
      }

      private int defense_ ;
      /**
       * <code>uint32 defense = 3;</code>
       */
      public int getDefense() {
        return defense_;
      }
      /**
       * <code>uint32 defense = 3;</code>
       */
      public Builder setDefense(int value) {
        
        defense_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 defense = 3;</code>
       */
      public Builder clearDefense() {
        
        defense_ = 0;
        onChanged();
        return this;
      }

      private int speed_ ;
      /**
       * <code>uint32 speed = 4;</code>
       */
      public int getSpeed() {
        return speed_;
      }
      /**
       * <code>uint32 speed = 4;</code>
       */
      public Builder setSpeed(int value) {
        
        speed_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 speed = 4;</code>
       */
      public Builder clearSpeed() {
        
        speed_ = 0;
        onChanged();
        return this;
      }

      private int hpUpgrade_ ;
      /**
       * <code>uint32 hp_upgrade = 5;</code>
       */
      public int getHpUpgrade() {
        return hpUpgrade_;
      }
      /**
       * <code>uint32 hp_upgrade = 5;</code>
       */
      public Builder setHpUpgrade(int value) {
        
        hpUpgrade_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 hp_upgrade = 5;</code>
       */
      public Builder clearHpUpgrade() {
        
        hpUpgrade_ = 0;
        onChanged();
        return this;
      }

      private int attackUpgrade_ ;
      /**
       * <code>uint32 attack_upgrade = 6;</code>
       */
      public int getAttackUpgrade() {
        return attackUpgrade_;
      }
      /**
       * <code>uint32 attack_upgrade = 6;</code>
       */
      public Builder setAttackUpgrade(int value) {
        
        attackUpgrade_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 attack_upgrade = 6;</code>
       */
      public Builder clearAttackUpgrade() {
        
        attackUpgrade_ = 0;
        onChanged();
        return this;
      }

      private int defenseUpgrade_ ;
      /**
       * <code>uint32 defense_upgrade = 7;</code>
       */
      public int getDefenseUpgrade() {
        return defenseUpgrade_;
      }
      /**
       * <code>uint32 defense_upgrade = 7;</code>
       */
      public Builder setDefenseUpgrade(int value) {
        
        defenseUpgrade_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 defense_upgrade = 7;</code>
       */
      public Builder clearDefenseUpgrade() {
        
        defenseUpgrade_ = 0;
        onChanged();
        return this;
      }

      private int speedUpgrade_ ;
      /**
       * <code>uint32 speed_upgrade = 8;</code>
       */
      public int getSpeedUpgrade() {
        return speedUpgrade_;
      }
      /**
       * <code>uint32 speed_upgrade = 8;</code>
       */
      public Builder setSpeedUpgrade(int value) {
        
        speedUpgrade_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 speed_upgrade = 8;</code>
       */
      public Builder clearSpeedUpgrade() {
        
        speedUpgrade_ = 0;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:ConstHeroTableItemExStarUpgradeFomulaStar7)
    }

    // @@protoc_insertion_point(class_scope:ConstHeroTableItemExStarUpgradeFomulaStar7)
    private static final com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7();
    }

    public static com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ConstHeroTableItemExStarUpgradeFomulaStar7>
        PARSER = new com.google.protobuf.AbstractParser<ConstHeroTableItemExStarUpgradeFomulaStar7>() {
      @java.lang.Override
      public ConstHeroTableItemExStarUpgradeFomulaStar7 parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ConstHeroTableItemExStarUpgradeFomulaStar7(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ConstHeroTableItemExStarUpgradeFomulaStar7> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ConstHeroTableItemExStarUpgradeFomulaStar7> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.ConstHeroTableItemExStarUpgradeFomulaStar7Protobuf.ConstHeroTableItemExStarUpgradeFomulaStar7 getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ConstHeroTableItemExStarUpgradeFomulaStar7_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ConstHeroTableItemExStarUpgradeFomulaStar7_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n0ConstHeroTableItemExStarUpgradeFomulaS" +
      "tar7.proto\"\304\001\n*ConstHeroTableItemExStarU" +
      "pgradeFomulaStar7\022\n\n\002hp\030\001 \001(\r\022\016\n\006attack\030" +
      "\002 \001(\r\022\017\n\007defense\030\003 \001(\r\022\r\n\005speed\030\004 \001(\r\022\022\n" +
      "\nhp_upgrade\030\005 \001(\r\022\026\n\016attack_upgrade\030\006 \001(" +
      "\r\022\027\n\017defense_upgrade\030\007 \001(\r\022\025\n\rspeed_upgr" +
      "ade\030\010 \001(\rBL\n\026com.gejian.pixel.protoB2Con" +
      "stHeroTableItemExStarUpgradeFomulaStar7P" +
      "rotobufb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_ConstHeroTableItemExStarUpgradeFomulaStar7_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ConstHeroTableItemExStarUpgradeFomulaStar7_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ConstHeroTableItemExStarUpgradeFomulaStar7_descriptor,
        new java.lang.String[] { "Hp", "Attack", "Defense", "Speed", "HpUpgrade", "AttackUpgrade", "DefenseUpgrade", "SpeedUpgrade", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
