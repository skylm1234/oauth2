// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ConstSkillTableItemExUpgradeConsumeFomulaX.proto

package com.gejian.pixel.proto;

public final class ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf {
  private ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ConstSkillTableItemExUpgradeConsumeFomulaXOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ConstSkillTableItemExUpgradeConsumeFomulaX)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>uint32 gold = 1;</code>
     */
    int getGold();

    /**
     * <code>uint32 book = 2;</code>
     */
    int getBook();
  }
  /**
   * Protobuf type {@code ConstSkillTableItemExUpgradeConsumeFomulaX}
   */
  public  static final class ConstSkillTableItemExUpgradeConsumeFomulaX extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ConstSkillTableItemExUpgradeConsumeFomulaX)
      ConstSkillTableItemExUpgradeConsumeFomulaXOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ConstSkillTableItemExUpgradeConsumeFomulaX.newBuilder() to construct.
    private ConstSkillTableItemExUpgradeConsumeFomulaX(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ConstSkillTableItemExUpgradeConsumeFomulaX() {
      gold_ = 0;
      book_ = 0;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ConstSkillTableItemExUpgradeConsumeFomulaX(
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

              gold_ = input.readUInt32();
              break;
            }
            case 16: {

              book_ = input.readUInt32();
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
      return com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.internal_static_ConstSkillTableItemExUpgradeConsumeFomulaX_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.internal_static_ConstSkillTableItemExUpgradeConsumeFomulaX_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX.class, com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX.Builder.class);
    }

    public static final int GOLD_FIELD_NUMBER = 1;
    private int gold_;
    /**
     * <code>uint32 gold = 1;</code>
     */
    public int getGold() {
      return gold_;
    }

    public static final int BOOK_FIELD_NUMBER = 2;
    private int book_;
    /**
     * <code>uint32 book = 2;</code>
     */
    public int getBook() {
      return book_;
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
      if (gold_ != 0) {
        output.writeUInt32(1, gold_);
      }
      if (book_ != 0) {
        output.writeUInt32(2, book_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (gold_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(1, gold_);
      }
      if (book_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, book_);
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
      if (!(obj instanceof com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX other = (com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX) obj;

      boolean result = true;
      result = result && (getGold()
          == other.getGold());
      result = result && (getBook()
          == other.getBook());
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
      hash = (37 * hash) + GOLD_FIELD_NUMBER;
      hash = (53 * hash) + getGold();
      hash = (37 * hash) + BOOK_FIELD_NUMBER;
      hash = (53 * hash) + getBook();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX parseFrom(
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
    public static Builder newBuilder(com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX prototype) {
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
     * Protobuf type {@code ConstSkillTableItemExUpgradeConsumeFomulaX}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ConstSkillTableItemExUpgradeConsumeFomulaX)
        com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaXOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.internal_static_ConstSkillTableItemExUpgradeConsumeFomulaX_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.internal_static_ConstSkillTableItemExUpgradeConsumeFomulaX_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX.class, com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX.newBuilder()
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
        gold_ = 0;

        book_ = 0;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.internal_static_ConstSkillTableItemExUpgradeConsumeFomulaX_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX getDefaultInstanceForType() {
        return com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX build() {
        com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX buildPartial() {
        com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX result = new com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX(this);
        result.gold_ = gold_;
        result.book_ = book_;
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
        if (other instanceof com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX) {
          return mergeFrom((com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX other) {
        if (other == com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX.getDefaultInstance()) return this;
        if (other.getGold() != 0) {
          setGold(other.getGold());
        }
        if (other.getBook() != 0) {
          setBook(other.getBook());
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
        com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int gold_ ;
      /**
       * <code>uint32 gold = 1;</code>
       */
      public int getGold() {
        return gold_;
      }
      /**
       * <code>uint32 gold = 1;</code>
       */
      public Builder setGold(int value) {
        
        gold_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 gold = 1;</code>
       */
      public Builder clearGold() {
        
        gold_ = 0;
        onChanged();
        return this;
      }

      private int book_ ;
      /**
       * <code>uint32 book = 2;</code>
       */
      public int getBook() {
        return book_;
      }
      /**
       * <code>uint32 book = 2;</code>
       */
      public Builder setBook(int value) {
        
        book_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 book = 2;</code>
       */
      public Builder clearBook() {
        
        book_ = 0;
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


      // @@protoc_insertion_point(builder_scope:ConstSkillTableItemExUpgradeConsumeFomulaX)
    }

    // @@protoc_insertion_point(class_scope:ConstSkillTableItemExUpgradeConsumeFomulaX)
    private static final com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX();
    }

    public static com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ConstSkillTableItemExUpgradeConsumeFomulaX>
        PARSER = new com.google.protobuf.AbstractParser<ConstSkillTableItemExUpgradeConsumeFomulaX>() {
      @java.lang.Override
      public ConstSkillTableItemExUpgradeConsumeFomulaX parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ConstSkillTableItemExUpgradeConsumeFomulaX(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ConstSkillTableItemExUpgradeConsumeFomulaX> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ConstSkillTableItemExUpgradeConsumeFomulaX> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.ConstSkillTableItemExUpgradeConsumeFomulaXProtobuf.ConstSkillTableItemExUpgradeConsumeFomulaX getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ConstSkillTableItemExUpgradeConsumeFomulaX_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ConstSkillTableItemExUpgradeConsumeFomulaX_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n0ConstSkillTableItemExUpgradeConsumeFom" +
      "ulaX.proto\"H\n*ConstSkillTableItemExUpgra" +
      "deConsumeFomulaX\022\014\n\004gold\030\001 \001(\r\022\014\n\004book\030\002" +
      " \001(\rBL\n\026com.gejian.pixel.protoB2ConstSki" +
      "llTableItemExUpgradeConsumeFomulaXProtob" +
      "ufb\006proto3"
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
    internal_static_ConstSkillTableItemExUpgradeConsumeFomulaX_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ConstSkillTableItemExUpgradeConsumeFomulaX_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ConstSkillTableItemExUpgradeConsumeFomulaX_descriptor,
        new java.lang.String[] { "Gold", "Book", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
