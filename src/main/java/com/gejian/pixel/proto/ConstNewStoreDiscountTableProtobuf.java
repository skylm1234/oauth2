// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ConstNewStoreDiscountTable.proto

package com.gejian.pixel.proto;

public final class ConstNewStoreDiscountTableProtobuf {
  private ConstNewStoreDiscountTableProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ConstNewStoreDiscountTableOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ConstNewStoreDiscountTable)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
     */
    java.util.List<com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx> 
        getItemsList();
    /**
     * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
     */
    com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx getItems(int index);
    /**
     * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
     */
    int getItemsCount();
    /**
     * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
     */
    java.util.List<? extends com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemExOrBuilder> 
        getItemsOrBuilderList();
    /**
     * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
     */
    com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemExOrBuilder getItemsOrBuilder(
        int index);
  }
  /**
   * Protobuf type {@code ConstNewStoreDiscountTable}
   */
  public  static final class ConstNewStoreDiscountTable extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ConstNewStoreDiscountTable)
      ConstNewStoreDiscountTableOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ConstNewStoreDiscountTable.newBuilder() to construct.
    private ConstNewStoreDiscountTable(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ConstNewStoreDiscountTable() {
      items_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ConstNewStoreDiscountTable(
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
            case 10: {
              if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
                items_ = new java.util.ArrayList<com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx>();
                mutable_bitField0_ |= 0x00000001;
              }
              items_.add(
                  input.readMessage(com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx.parser(), extensionRegistry));
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
        if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
          items_ = java.util.Collections.unmodifiableList(items_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.internal_static_ConstNewStoreDiscountTable_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.internal_static_ConstNewStoreDiscountTable_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable.class, com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable.Builder.class);
    }

    public static final int ITEMS_FIELD_NUMBER = 1;
    private java.util.List<com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx> items_;
    /**
     * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
     */
    public java.util.List<com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx> getItemsList() {
      return items_;
    }
    /**
     * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
     */
    public java.util.List<? extends com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemExOrBuilder> 
        getItemsOrBuilderList() {
      return items_;
    }
    /**
     * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
     */
    public int getItemsCount() {
      return items_.size();
    }
    /**
     * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
     */
    public com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx getItems(int index) {
      return items_.get(index);
    }
    /**
     * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
     */
    public com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemExOrBuilder getItemsOrBuilder(
        int index) {
      return items_.get(index);
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
      for (int i = 0; i < items_.size(); i++) {
        output.writeMessage(1, items_.get(i));
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      for (int i = 0; i < items_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, items_.get(i));
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
      if (!(obj instanceof com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable other = (com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable) obj;

      boolean result = true;
      result = result && getItemsList()
          .equals(other.getItemsList());
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
      if (getItemsCount() > 0) {
        hash = (37 * hash) + ITEMS_FIELD_NUMBER;
        hash = (53 * hash) + getItemsList().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable parseFrom(
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
    public static Builder newBuilder(com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable prototype) {
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
     * Protobuf type {@code ConstNewStoreDiscountTable}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ConstNewStoreDiscountTable)
        com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTableOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.internal_static_ConstNewStoreDiscountTable_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.internal_static_ConstNewStoreDiscountTable_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable.class, com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable.newBuilder()
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
          getItemsFieldBuilder();
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        if (itemsBuilder_ == null) {
          items_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          itemsBuilder_.clear();
        }
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.internal_static_ConstNewStoreDiscountTable_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable getDefaultInstanceForType() {
        return com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable build() {
        com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable buildPartial() {
        com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable result = new com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable(this);
        int from_bitField0_ = bitField0_;
        if (itemsBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001)) {
            items_ = java.util.Collections.unmodifiableList(items_);
            bitField0_ = (bitField0_ & ~0x00000001);
          }
          result.items_ = items_;
        } else {
          result.items_ = itemsBuilder_.build();
        }
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
        if (other instanceof com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable) {
          return mergeFrom((com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable other) {
        if (other == com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable.getDefaultInstance()) return this;
        if (itemsBuilder_ == null) {
          if (!other.items_.isEmpty()) {
            if (items_.isEmpty()) {
              items_ = other.items_;
              bitField0_ = (bitField0_ & ~0x00000001);
            } else {
              ensureItemsIsMutable();
              items_.addAll(other.items_);
            }
            onChanged();
          }
        } else {
          if (!other.items_.isEmpty()) {
            if (itemsBuilder_.isEmpty()) {
              itemsBuilder_.dispose();
              itemsBuilder_ = null;
              items_ = other.items_;
              bitField0_ = (bitField0_ & ~0x00000001);
              itemsBuilder_ = 
                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                   getItemsFieldBuilder() : null;
            } else {
              itemsBuilder_.addAllMessages(other.items_);
            }
          }
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
        com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.util.List<com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx> items_ =
        java.util.Collections.emptyList();
      private void ensureItemsIsMutable() {
        if (!((bitField0_ & 0x00000001) == 0x00000001)) {
          items_ = new java.util.ArrayList<com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx>(items_);
          bitField0_ |= 0x00000001;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilderV3<
          com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx, com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx.Builder, com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemExOrBuilder> itemsBuilder_;

      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public java.util.List<com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx> getItemsList() {
        if (itemsBuilder_ == null) {
          return java.util.Collections.unmodifiableList(items_);
        } else {
          return itemsBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public int getItemsCount() {
        if (itemsBuilder_ == null) {
          return items_.size();
        } else {
          return itemsBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx getItems(int index) {
        if (itemsBuilder_ == null) {
          return items_.get(index);
        } else {
          return itemsBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public Builder setItems(
          int index, com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx value) {
        if (itemsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureItemsIsMutable();
          items_.set(index, value);
          onChanged();
        } else {
          itemsBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public Builder setItems(
          int index, com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx.Builder builderForValue) {
        if (itemsBuilder_ == null) {
          ensureItemsIsMutable();
          items_.set(index, builderForValue.build());
          onChanged();
        } else {
          itemsBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public Builder addItems(com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx value) {
        if (itemsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureItemsIsMutable();
          items_.add(value);
          onChanged();
        } else {
          itemsBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public Builder addItems(
          int index, com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx value) {
        if (itemsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureItemsIsMutable();
          items_.add(index, value);
          onChanged();
        } else {
          itemsBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public Builder addItems(
          com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx.Builder builderForValue) {
        if (itemsBuilder_ == null) {
          ensureItemsIsMutable();
          items_.add(builderForValue.build());
          onChanged();
        } else {
          itemsBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public Builder addItems(
          int index, com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx.Builder builderForValue) {
        if (itemsBuilder_ == null) {
          ensureItemsIsMutable();
          items_.add(index, builderForValue.build());
          onChanged();
        } else {
          itemsBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public Builder addAllItems(
          java.lang.Iterable<? extends com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx> values) {
        if (itemsBuilder_ == null) {
          ensureItemsIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, items_);
          onChanged();
        } else {
          itemsBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public Builder clearItems() {
        if (itemsBuilder_ == null) {
          items_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
          onChanged();
        } else {
          itemsBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public Builder removeItems(int index) {
        if (itemsBuilder_ == null) {
          ensureItemsIsMutable();
          items_.remove(index);
          onChanged();
        } else {
          itemsBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx.Builder getItemsBuilder(
          int index) {
        return getItemsFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemExOrBuilder getItemsOrBuilder(
          int index) {
        if (itemsBuilder_ == null) {
          return items_.get(index);  } else {
          return itemsBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public java.util.List<? extends com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemExOrBuilder> 
           getItemsOrBuilderList() {
        if (itemsBuilder_ != null) {
          return itemsBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(items_);
        }
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx.Builder addItemsBuilder() {
        return getItemsFieldBuilder().addBuilder(
            com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx.getDefaultInstance());
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx.Builder addItemsBuilder(
          int index) {
        return getItemsFieldBuilder().addBuilder(
            index, com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx.getDefaultInstance());
      }
      /**
       * <code>repeated .ConstNewStoreDiscountTableItemEx items = 1;</code>
       */
      public java.util.List<com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx.Builder> 
           getItemsBuilderList() {
        return getItemsFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilderV3<
          com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx, com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx.Builder, com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemExOrBuilder> 
          getItemsFieldBuilder() {
        if (itemsBuilder_ == null) {
          itemsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
              com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx, com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemEx.Builder, com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.ConstNewStoreDiscountTableItemExOrBuilder>(
                  items_,
                  ((bitField0_ & 0x00000001) == 0x00000001),
                  getParentForChildren(),
                  isClean());
          items_ = null;
        }
        return itemsBuilder_;
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


      // @@protoc_insertion_point(builder_scope:ConstNewStoreDiscountTable)
    }

    // @@protoc_insertion_point(class_scope:ConstNewStoreDiscountTable)
    private static final com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable();
    }

    public static com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ConstNewStoreDiscountTable>
        PARSER = new com.google.protobuf.AbstractParser<ConstNewStoreDiscountTable>() {
      @java.lang.Override
      public ConstNewStoreDiscountTable parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ConstNewStoreDiscountTable(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ConstNewStoreDiscountTable> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ConstNewStoreDiscountTable> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.ConstNewStoreDiscountTableProtobuf.ConstNewStoreDiscountTable getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ConstNewStoreDiscountTable_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ConstNewStoreDiscountTable_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n ConstNewStoreDiscountTable.proto\032&Cons" +
      "tNewStoreDiscountTableItemEx.proto\"N\n\032Co" +
      "nstNewStoreDiscountTable\0220\n\005items\030\001 \003(\0132" +
      "!.ConstNewStoreDiscountTableItemExB<\n\026co" +
      "m.gejian.pixel.protoB\"ConstNewStoreDisco" +
      "untTableProtobufb\006proto3"
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
          com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.getDescriptor(),
        }, assigner);
    internal_static_ConstNewStoreDiscountTable_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ConstNewStoreDiscountTable_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ConstNewStoreDiscountTable_descriptor,
        new java.lang.String[] { "Items", });
    com.gejian.pixel.proto.ConstNewStoreDiscountTableItemExProtobuf.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
