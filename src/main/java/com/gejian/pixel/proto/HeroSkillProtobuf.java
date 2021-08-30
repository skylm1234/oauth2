// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: HeroSkill.proto

package com.gejian.pixel.proto;

public final class HeroSkillProtobuf {
  private HeroSkillProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface HeroSkillOrBuilder extends
      // @@protoc_insertion_point(interface_extends:HeroSkill)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string type = 10;</code>
     */
    java.lang.String getType();
    /**
     * <code>string type = 10;</code>
     */
    com.google.protobuf.ByteString
        getTypeBytes();

    /**
     * <code>uint32 level = 11;</code>
     */
    int getLevel();
  }
  /**
   * Protobuf type {@code HeroSkill}
   */
  public  static final class HeroSkill extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:HeroSkill)
      HeroSkillOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use HeroSkill.newBuilder() to construct.
    private HeroSkill(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private HeroSkill() {
      type_ = "";
      level_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private HeroSkill(
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
            case 82: {
              java.lang.String s = input.readStringRequireUtf8();

              type_ = s;
              break;
            }
            case 88: {

              level_ = input.readUInt32();
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
      return com.gejian.pixel.proto.HeroSkillProtobuf.internal_static_HeroSkill_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.HeroSkillProtobuf.internal_static_HeroSkill_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill.class, com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill.Builder.class);
    }

    public static final int TYPE_FIELD_NUMBER = 10;
    private volatile java.lang.Object type_;
    /**
     * <code>string type = 10;</code>
     */
    public java.lang.String getType() {
      java.lang.Object ref = type_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        type_ = s;
        return s;
      }
    }
    /**
     * <code>string type = 10;</code>
     */
    public com.google.protobuf.ByteString
        getTypeBytes() {
      java.lang.Object ref = type_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        type_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int LEVEL_FIELD_NUMBER = 11;
    private int level_;
    /**
     * <code>uint32 level = 11;</code>
     */
    public int getLevel() {
      return level_;
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
      if (!getTypeBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 10, type_);
      }
      if (level_ != 0) {
        output.writeUInt32(11, level_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getTypeBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(10, type_);
      }
      if (level_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(11, level_);
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
      if (!(obj instanceof com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill other = (com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill) obj;

      boolean result = true;
      result = result && getType()
          .equals(other.getType());
      result = result && (getLevel()
          == other.getLevel());
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
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType().hashCode();
      hash = (37 * hash) + LEVEL_FIELD_NUMBER;
      hash = (53 * hash) + getLevel();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill parseFrom(
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
    public static Builder newBuilder(com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill prototype) {
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
     * Protobuf type {@code HeroSkill}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:HeroSkill)
        com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkillOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.HeroSkillProtobuf.internal_static_HeroSkill_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.HeroSkillProtobuf.internal_static_HeroSkill_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill.class, com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill.newBuilder()
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
        type_ = "";

        level_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.HeroSkillProtobuf.internal_static_HeroSkill_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill getDefaultInstanceForType() {
        return com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill build() {
        com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill buildPartial() {
        com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill result = new com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill(this);
        result.type_ = type_;
        result.level_ = level_;
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
        if (other instanceof com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill) {
          return mergeFrom((com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill other) {
        if (other == com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill.getDefaultInstance()) return this;
        if (!other.getType().isEmpty()) {
          type_ = other.type_;
          onChanged();
        }
        if (other.getLevel() != 0) {
          setLevel(other.getLevel());
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
        com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object type_ = "";
      /**
       * <code>string type = 10;</code>
       */
      public java.lang.String getType() {
        java.lang.Object ref = type_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          type_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string type = 10;</code>
       */
      public com.google.protobuf.ByteString
          getTypeBytes() {
        java.lang.Object ref = type_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          type_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string type = 10;</code>
       */
      public Builder setType(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string type = 10;</code>
       */
      public Builder clearType() {
        
        type_ = getDefaultInstance().getType();
        onChanged();
        return this;
      }
      /**
       * <code>string type = 10;</code>
       */
      public Builder setTypeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        type_ = value;
        onChanged();
        return this;
      }

      private int level_ ;
      /**
       * <code>uint32 level = 11;</code>
       */
      public int getLevel() {
        return level_;
      }
      /**
       * <code>uint32 level = 11;</code>
       */
      public Builder setLevel(int value) {
        
        level_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 level = 11;</code>
       */
      public Builder clearLevel() {
        
        level_ = 0;
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


      // @@protoc_insertion_point(builder_scope:HeroSkill)
    }

    // @@protoc_insertion_point(class_scope:HeroSkill)
    private static final com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill();
    }

    public static com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<HeroSkill>
        PARSER = new com.google.protobuf.AbstractParser<HeroSkill>() {
      @java.lang.Override
      public HeroSkill parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new HeroSkill(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<HeroSkill> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<HeroSkill> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.HeroSkillProtobuf.HeroSkill getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_HeroSkill_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_HeroSkill_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017HeroSkill.proto\"(\n\tHeroSkill\022\014\n\004type\030\n" +
      " \001(\t\022\r\n\005level\030\013 \001(\rB+\n\026com.gejian.pixel." +
      "protoB\021HeroSkillProtobufb\006proto3"
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
    internal_static_HeroSkill_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_HeroSkill_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_HeroSkill_descriptor,
        new java.lang.String[] { "Type", "Level", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}